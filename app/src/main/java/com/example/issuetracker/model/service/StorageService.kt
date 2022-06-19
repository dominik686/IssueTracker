package com.example.issuetracker.model.service

import com.example.issuetracker.model.ProjectsListModel

interface StorageService {
    fun addUserAndDefaultProjectsDebug()

    fun fetchProjectsDebug(onSuccess: (List<ProjectsListModel>) ->Unit) : List<ProjectsListModel>
    fun addProject()

}