package com.dominikwieczynski.issuetracker.model.service

import com.dominikwieczynski.issuetracker.model.Issue
import com.dominikwieczynski.issuetracker.model.Project
import com.dominikwieczynski.issuetracker.model.User

interface StorageService {
    fun addUser(username: String, onSuccess: () -> Unit,
                onFailure: () -> Unit)

    fun addIssueAddedListener(projectId : String, onDocumentEvent: (Issue) -> Unit,
    onError: (Throwable) -> Unit)
    fun removeIssueAddedListener()

    fun addProjectAddedListener(onDocumentEvent: (User) -> Unit, onError: (Throwable) -> Unit)
    fun removeProjectAddedListener()

    fun checkIfUsernameExists(username: String, onResult: (Boolean) -> Unit)
    fun addUserAndDefaultProjectsDebug()
    fun fetchProjects(onSuccess: (List<User>) -> Unit)
    fun addProject(project: Project, onSuccess: () -> Unit, onFailure: (Throwable) -> Unit)
    fun addIssue(issue : Issue,  projectId : String)
    fun fetchIssues(projectId: String, onSuccess: (List<Issue>) -> Unit)

}