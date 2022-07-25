package com.dominikwieczynski.issuetracker.model.service.impl

import android.util.Log
import com.dominikwieczynski.issuetracker.model.ProjectPublic
import com.dominikwieczynski.issuetracker.model.User
import com.dominikwieczynski.issuetracker.model.service.StorageService
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import kotlin.random.Random

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

    override fun addProject(project: ProjectPublic, onSuccess: () -> Unit, onFailure: (Throwable) -> Unit) {
        val user = Firebase.auth.currentUser!!

            db.collection(USERS_COLLECTION)
                .whereEqualTo("UID", user.uid)
                .get()
                .addOnFailureListener{ e->
                onFailure(e)
            }
                .addOnSuccessListener { result ->

                val addProjectToProjects =FieldValue.arrayUnion(hashMapOf(
                    /*In the future the ID will be the same id as in the projects collection

                     */
                    "id" to Random.nextInt(),
                    "name" to project.name,
                    "description" to project.description,
                ))

                Log.d("StorageService", "isEmpty: ${result.isEmpty} ")
                for(document in result )
                {
                    document.reference.update("projects", addProjectToProjects)
                        .addOnSuccessListener {
                        onSuccess()
                    }
                        .addOnFailureListener{ e->
                            onFailure(e)
                        }
                }

            }


    }
}