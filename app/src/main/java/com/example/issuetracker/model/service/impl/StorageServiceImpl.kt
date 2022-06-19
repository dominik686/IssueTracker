package com.example.issuetracker.model.service.impl

import android.util.Log
import com.example.issuetracker.model.ProjectsListModel
import com.example.issuetracker.model.service.StorageService
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class StorageServiceImpl @Inject constructor() : StorageService {


    // Maybe I should just put everything in the same collection? Need to rewatch structuring data in firebase
    //You add the users entry, and after it is finished you add the projects subcollection?
    override fun addUserAndDefaultProjectsDebug() {

        val db = Firebase.firestore
        val user = Firebase.auth.currentUser!!
        val usersEntry = hashMapOf(
            "UID" to user.uid,
            "projects" to listOf<ProjectsListModel>(ProjectsListModel(), ProjectsListModel() )
        )


        db.collection("users").add(usersEntry).addOnSuccessListener { query ->
            Log.d("StorageService", "Add successful")

        }.addOnFailureListener{ exception ->
            Log.d("StorageService", "Add failed + $exception")
        }
    }

    override fun fetchProjectsDebug(onSuccess: (List<ProjectsListModel>) -> Unit) : List<ProjectsListModel> {
        val db = Firebase.firestore
        val user = Firebase.auth.currentUser!!
        db.collection("users").whereEqualTo("UID", user.uid).get().addOnSuccessListener { result ->
            onSuccess(result.toObjects() ?: listOf(ProjectsListModel()))
        }
        return emptyList()
    }

    override fun addProject() {
        TODO("Not yet implemented")
    }
}