package com.dominikwieczynski.issuetracker.ui.screens.project_list

import androidx.compose.runtime.mutableStateOf
import com.dominikwieczynski.issuetracker.IssueTrackerViewModel
import com.dominikwieczynski.issuetracker.model.ProjectPublic
import com.dominikwieczynski.issuetracker.model.service.LogService
import com.dominikwieczynski.issuetracker.model.service.StorageService
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
            uiState.value = uiState.value.copy(projects = it[0].projects.toMutableList(), listFetched = true)
        }
    }



    fun openDialog() {
        uiState.value = uiState.value.copy(dialogOpen = true)
    }
    fun closeDialog()
    {
        uiState.value = uiState.value.copy(dialogOpen= false)

    }

    fun updateProjects(name: String, description: String)
    {
        val project = ProjectPublic(name, description)
        val newList = uiState.value.projects
        newList.add(project)
        uiState.value = uiState.value.copy(projects = newList)
    }
    fun onAddPressed(name: String, description: String) {
        val newProject = ProjectPublic(name = name, description = description)
        storageService.addProject(newProject, onSuccess = {
           val newList = uiState.value.projects
            newList.add(newProject)
            uiState.value = uiState.value.copy(projects = newList)
        },
        onFailure = {e->
            logService.logNonFatalException(e)})

    }


}