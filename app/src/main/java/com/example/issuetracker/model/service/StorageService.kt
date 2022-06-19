package com.example.issuetracker.model.service

import com.example.issuetracker.model.ProjectPublic

interface StorageService {
    fun addUser(username: String)

    fun addUserAndDefaultProjectsDebug()

    fun fetchProjectsDebug(onSuccess: (List<ProjectPublic>) ->Unit) : List<ProjectPublic>
    fun addProject(project: ProjectPublic)

}