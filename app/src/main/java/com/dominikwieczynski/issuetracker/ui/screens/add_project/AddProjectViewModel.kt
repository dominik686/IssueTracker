package com.dominikwieczynski.issuetracker.ui.screens.add_project

import androidx.compose.runtime.mutableStateOf
import com.dominikwieczynski.issuetracker.IssueTrackerViewModel
import com.dominikwieczynski.issuetracker.model.Project
import com.dominikwieczynski.issuetracker.model.service.LogService
import com.dominikwieczynski.issuetracker.model.service.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddProjectViewModel @Inject constructor(var storageService: StorageService,
var logService: LogService)
    : IssueTrackerViewModel(logService)
{
    var uiState = mutableStateOf(AddProjectUiState())
    fun onAddPressed(name: String, description: String) {
        val newProject = Project(name = name, description = description)
        storageService.addProject(newProject, onSuccess = {
        },
            onFailure = {e->
                logService.logNonFatalException(e)})

    }
}