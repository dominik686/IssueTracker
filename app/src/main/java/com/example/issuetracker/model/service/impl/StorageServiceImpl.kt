package com.example.issuetracker.model.service.impl

import android.util.Log
import com.example.issuetracker.model.ProjectPublic
import com.example.issuetracker.model.service.StorageService
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class StorageServiceImpl @Inject constructor() : StorageService {
    private val db = Firebase.firestore

    companion object
    {
       private const val USERS_COLLECTION = "users"

    }
    override fun addUser(username: String, onSuccess: () -> Unit,
                         onFailure: () -> Unit
    ) {
         val user = Firebase.auth.currentUser!!

        val usersEntry = hashMapOf(
            "UID" to user.uid,
            "username" to username,
            "projects" to emptyList<ProjectPublic>()
        )
        db.collection(USERS_COLLECTION).add(usersEntry)
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


    // Maybe I should just put everything in the same collection? Need to rewatch structuring data in firebase
    //You add the users entry, and after it is finished you add the projects subcollection?
    override fun addUserAndDefaultProjectsDebug() {

        val db = Firebase.firestore
        val user = Firebase.auth.currentUser!!
        val usersEntry = hashMapOf(
            "UID" to user.uid,
            "projects" to listOf(ProjectPublic(), ProjectPublic() )
        )


        db.collection("users").add(usersEntry).addOnSuccessListener { query ->
            Log.d("StorageService", "Add successful")

        }.addOnFailureListener{ exception ->
            Log.d("StorageService", "Add failed + $exception")
        }
    }

    override fun fetchProjectsDebug(onSuccess: (List<ProjectPublic>) -> Unit) : List<ProjectPublic> {
        val db = Firebase.firestore
        val user = Firebase.auth.currentUser!!
        db.collection("users").whereEqualTo("UID", user.uid).get().addOnSuccessListener { result ->
            onSuccess(result.toObjects() ?: listOf(ProjectPublic()))
        }
        return emptyList()
    }

    override fun addProject(project: ProjectPublic) {
        val user = Firebase.auth.currentUser!!


            db.collection(USERS_COLLECTION).whereEqualTo("uid", user.uid).get().addOnSuccessListener { result ->
                val addProjectToProjects =FieldValue.arrayUnion(project)
                for(document in result ) document.reference.update("projects", addProjectToProjects)
            }

    }
}