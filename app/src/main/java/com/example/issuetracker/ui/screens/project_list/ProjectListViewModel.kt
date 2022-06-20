package com.example.issuetracker.ui.screens.project_list

import androidx.compose.runtime.mutableStateOf
import com.example.issuetracker.IssueTrackerViewModel
import com.example.issuetracker.model.ProjectPublic
import com.example.issuetracker.model.service.LogService
import com.example.issuetracker.model.service.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProjectListViewModel @Inject constructor(
    private val storageService: StorageService,
    private val logService: LogService,

    ): IssueTrackerViewModel()
{

    /* for some reason projects is not updated correctly and the ui doesnt recomposition

     */
    val uiState = mutableStateOf(ProjectListUiState())

    fun onBackArrowPressed(popUp: () -> Unit) {
        popUp()
    }


    fun getProjects()
    {
        storageService.fetchProjects {
            uiState.value = uiState.value.copy(projects = it[0].projects.toMutableList())
        }
    }



    fun openDialog() {
        uiState.value = uiState.value.copy(dialogOpen = true)
    }
    fun closeDialog()
    {
        uiState.value = uiState.value.copy(dialogOpen= false)

    }

    fun onAddPressed(name: String, description: String) {
        val newProject = ProjectPublic(name = name, description = description)
        storageService.addProject(newProject, onSuccess = {
            /*
            any updates here dont work for some reason
            val newList = uiState.value.projects
           newList.add(newProject)
           uiState.value = uiState.value.copy(projects = newList)

             */


        },
        onFailure = {e->
            logService.logNonFatalException(e)})

    }


}