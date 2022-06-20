package com.example.issuetracker.model.service

import com.example.issuetracker.model.ProjectPublic
import com.example.issuetracker.model.User

interface StorageService {
    fun addUser(username: String, onSuccess: () -> Unit,
                onFailure: () -> Unit)

    fun checkIfUsernameExists(username: String, onResult: (Boolean) -> Unit)

    fun addUserAndDefaultProjectsDebug()

    fun fetchProjects(onSuccess: (List<User>) -> Unit)
    fun addProject(project: ProjectPublic, onSuccess: () -> Unit, onFailure: (Throwable) -> Unit)

}