package com.dominikwieczynski.issuetracker.model.service.impl

import android.util.Log
import com.dominikwieczynski.issuetracker.model.Issue
import com.dominikwieczynski.issuetracker.model.Project
import com.dominikwieczynski.issuetracker.model.User
import com.dominikwieczynski.issuetracker.model.service.StorageService
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class StorageServiceImpl @Inject constructor() : StorageService {
    private val db = Firebase.firestore
    private var listenerRegistration : ListenerRegistration? = null
    companion object
    {
        private const val USERS_COLLECTION = "users"
        private const val PROJECTS_COLLECTION = "projects"
        private const val ISSUES_COLLECTION = "issues"
    }


    override fun addUser(username: String, onSuccess: () -> Unit,
                         onFailure: () -> Unit
    ) {
         val user = Firebase.auth.currentUser!!

        val usersEntry = hashMapOf(
            "UID" to user.uid,
            "username" to username,
            "projects" to emptyList<Project>()
        )
        db.collection(USERS_COLLECTION).add(usersEntry)
    }

    override fun deleteAllUserData(UID: String, onResult: () -> Unit) {
        deleteUser(UID) {
                deleteProjects(UID) {onResult()}
        }
    }
    private fun deleteUser(UID: String, onResult: () -> Unit)
    {
        val userQuery = db.collection(USERS_COLLECTION).whereEqualTo("UID", UID)
            .addSnapshotListener{ query, exception ->
                var docs = query?.documents
                query?.documents?.forEach{ document->
                    document.reference.delete()
                }
                onResult()

            }
    }
    private fun deleteProjects(UID: String, onResult: () -> Unit)
    {
        val projectsQuery = db.collection(PROJECTS_COLLECTION).whereEqualTo("UID", UID)
            .addSnapshotListener{ query, exception ->
                var docs = query?.documents
                query?.documents?.forEach{ document->
                    document.reference.delete()
                    query.documents.forEach{
                        deleteIssues(it.id)
                    }
                }
                onResult()
            }
    }
    private fun deleteIssues(projectId: String)
    {
        val projectsQuery = db.collection(ISSUES_COLLECTION).whereEqualTo("projectId", projectId)
            .addSnapshotListener{ query, exception ->
                var docs = query?.documents

                query?.documents?.forEach{ document->
                    document.reference.delete()

                }
            }
    }

    override fun addIssueAddedListener(
        projectId: String,
        onDocumentEvent: (Issue) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        // Maybe whereEqual to is broken?
       val query = Firebase.firestore.collection(ISSUES_COLLECTION).whereEqualTo("projectId", projectId)

        listenerRegistration = query.addSnapshotListener{value, error ->
            if(error != null){
                onError(error)
                return@addSnapshotListener
            }

            value?.documentChanges?.forEach {
                onDocumentEvent(it.document.toObject<Issue>())
            }
        }
    }


    override fun removeIssueAddedListener() {
        listenerRegistration?.remove()
    }

    override fun addProjectAddedListener(
        onDocumentEvent: (User) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        var query =  Firebase.firestore.collection(USERS_COLLECTION).whereEqualTo("UID", Firebase.auth.currentUser?.uid)

        listenerRegistration = query.addSnapshotListener{value, error ->
            if(error != null)
            {
                onError(error)
            }
            value?.documentChanges?.forEach{

                onDocumentEvent(it.document.toObject<User>())
            }
        }
    }

    override fun removeProjectAddedListener() {
        listenerRegistration?.remove()
    }

    override fun checkIfUsernameExists(username: String, onResult: (Boolean) -> Unit) {
        db.collection(USERS_COLLECTION).whereEqualTo("username", username).get().addOnSuccessListener {
            if(it.isEmpty)
            {
                onResult(false)
            }
            else
            {
                onResult(true)
            }
        }
            .addOnFailureListener{
                onResult(false)

            }
    }



    override fun addUserAndDefaultProjectsDebug() {

        val db = Firebase.firestore
        val user = Firebase.auth.currentUser!!
        val usersEntry = hashMapOf(
            "UID" to user.uid,
            "projects" to listOf(Project(), Project() )
        )


        db.collection("users").add(usersEntry).addOnSuccessListener { query ->
            Log.d("StorageService", "Add successful")

        }.addOnFailureListener{ exception ->
            Log.d("StorageService", "Add failed + $exception")
        }
    }

    // Need to get specific field
    override fun fetchProjects(onSuccess: (List<User>) -> Unit){
        val db = Firebase.firestore
        val user = Firebase.auth.currentUser!!
        db.collection("users").whereEqualTo("UID", user.uid).get().addOnSuccessListener { result ->
            // THis is null which is why listOf(ProjectPublic() is displayed (default)
            if(result.isEmpty)
            {

            }
            else
            {
                //onSuccess((result.toObjects() ?: User()) as List<ProjectPublic>)
               val d =  result.toObjects<User>()
                onSuccess(d)
            }
        }

    }

    override fun addProject(project: Project, onSuccess: () -> Unit, onFailure: (Throwable) -> Unit) {
        val user = Firebase.auth.currentUser!!

        val projectsCollectionNewEntry = hashMapOf(
            "UID" to user.uid,
            "name" to project.name,
            "description" to project.description,
            "languages" to project.languages
        )
        db.collection(PROJECTS_COLLECTION).add(projectsCollectionNewEntry)
            .addOnSuccessListener { query ->
                project.id = query.id
                addProjectToUserCollection(project = project, userId = user.uid,onFailure = onFailure, onSuccess = onSuccess)

        }

    }
    private fun addProjectToUserCollection(project : Project, userId: String, onSuccess: () -> Unit, onFailure: (Throwable) -> Unit)
    {
        db.collection(USERS_COLLECTION)
            .whereEqualTo("UID", userId)
            .get()
            .addOnFailureListener{ e->
                onFailure(e)
            }
            .addOnSuccessListener { userQuery ->

                val projectsArray =FieldValue.arrayUnion(hashMapOf(

                    "id" to project.id,
                    "name" to project.name,
                    "description" to project.description,
                    "languages" to project.languages
                ))

                Log.d("StorageService", "isEmpty: ${userQuery.isEmpty} ")
                for(document in userQuery )
                {
                    document.reference.update("projects", projectsArray)
                        .addOnSuccessListener {
                            onSuccess()
                        }
                        .addOnFailureListener{ e->
                            onFailure(e)
                        }
                }

            }
    }
    override fun addIssue(issue: Issue, projectId : String) {
        val issueEntry = hashMapOf(
            "projectId" to projectId,
            "name" to issue.name,
            "description" to issue.description,
            "label" to issue.label)
        db.collection(ISSUES_COLLECTION).add(issueEntry)
            .addOnSuccessListener {  query ->
                issue.id = query.id
                addIssueToProjectsEntry(issue, projectId)
        }
    }

    override fun fetchIssues(projectId: String, onSuccess: (List<Issue>) -> Unit) {
        db.collection(PROJECTS_COLLECTION).document(projectId).get().addOnSuccessListener { query ->
            var issuesRaw =  query.get("issues") as List<HashMap<String, String>>? ?: emptyList()

            var listOfIssues = mutableListOf<Issue>()
            for(issue in issuesRaw)
            {
                listOfIssues.add(Issue(id = issue["issueId"]!!, name = issue["name"]!!, description = issue["description"]!!, label = issue["label"]!! ))
            }
            // THIS BECOMES AN ARRAY LIST OF HASH MAPS
         //   onSuccess(issues)
            onSuccess(listOfIssues)
        }
    }

    private fun addIssueToProjectsEntry(issue: Issue,projectId : String)
    {
        val issueEntry = FieldValue.arrayUnion(hashMapOf(
            "issueId" to issue.id,
            "name" to issue.name,
            "description" to issue.description,
            "label" to issue.label
        ))
        db.collection(PROJECTS_COLLECTION).document(projectId).update("issues", issueEntry).addOnSuccessListener { query ->

        }

    }
}