package com.dominikwieczynski.issuetracker.model.service

import com.dominikwieczynski.issuetracker.model.Issue
import com.dominikwieczynski.issuetracker.model.ProjectPublic
import com.dominikwieczynski.issuetracker.model.User

interface StorageService {
    fun addUser(username: String, onSuccess: () -> Unit,
                onFailure: () -> Unit)

    fun checkIfUsernameExists(username: String, onResult: (Boolean) -> Unit)

    fun addUserAndDefaultProjectsDebug()

    fun fetchProjects(onSuccess: (List<User>) -> Unit)
    fun addProject(project: ProjectPublic, onSuccess: () -> Unit, onFailure: (Throwable) -> Unit)
    fun addIssue(issue : Issue,  projectId : String)
    fun fetchIssues(projectId: String, onSuccess: (List<Issue>) -> Unit)

}