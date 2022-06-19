package com.example.issuetracker.model.service

import com.example.issuetracker.model.ProjectPublic

interface StorageService {
    fun addUser(username: String, onSuccess: () -> Unit,
                onFailure: () -> Unit)

    fun checkIfUsernameExists(username: String, onResult: (Boolean) -> Unit)

    fun addUserAndDefaultProjectsDebug()

    fun fetchProjectsDebug(onSuccess: (List<ProjectPublic>) ->Unit) : List<ProjectPublic>
    fun addProject(project: ProjectPublic)

}