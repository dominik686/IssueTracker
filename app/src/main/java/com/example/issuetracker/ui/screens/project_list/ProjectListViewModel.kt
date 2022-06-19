package com.example.issuetracker.ui.screens.project_list

import androidx.compose.runtime.mutableStateOf
import com.example.issuetracker.IssueTrackerViewModel
import com.example.issuetracker.model.ProjectsListModel
import com.example.issuetracker.model.service.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProjectListViewModel @Inject constructor(private val storageService: StorageService): IssueTrackerViewModel()
{
    val uiState = mutableStateOf(ProjectListUiState())
    fun onBackArrowPressed(popUp: () -> Unit) {
        popUp()
    }

    fun addUser()
    {
        storageService.addUserAndDefaultProjectsDebug()
    }

    fun getProjects()
    {
        storageService.fetchProjectsDebug {
            uiState.value = ProjectListUiState(it)
        }
    }

    fun addNewProject() {
    }

    fun fabPressed() {
        uiState.value = ProjectListUiState(uiState.value.projects, true)
    }
}