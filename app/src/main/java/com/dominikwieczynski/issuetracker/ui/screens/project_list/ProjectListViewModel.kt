package com.dominikwieczynski.issuetracker.ui.screens.project_list

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.dominikwieczynski.issuetracker.IssueTrackerViewModel
import com.dominikwieczynski.issuetracker.model.Project
import com.dominikwieczynski.issuetracker.model.User
import com.dominikwieczynski.issuetracker.model.service.LogService
import com.dominikwieczynski.issuetracker.model.service.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectListViewModel @Inject constructor(
    private val storageService: StorageService,
    private val logService: LogService,

    ): IssueTrackerViewModel(logService)
{

    /* for some reason projects is not updated correctly and the ui doesnt recomposition

     */
    val uiState = mutableStateOf(ProjectListUiState())
    var projects = mutableStateListOf<Project>()

    fun addProjectAddedListener()
    {
        viewModelScope.launch {
            storageService.addProjectAddedListener(::onDocumentEvent, ::onError)
        }
    }
    fun removeProjectAddedListener()
    {
        viewModelScope.launch {
            storageService.removeProjectAddedListener()
        }
    }
    private fun onDocumentEvent(user: User) {

        user.projects.forEach{
            if(!projects.contains(it))
            {
                Log.d("ProjectList", "Project added")
                projects.add(it)
            }
        }
        if(!uiState.value.listFetched)
            uiState.value = uiState.value.copy(listFetched = true)
    }
    fun onBackArrowPressed(popUp: () -> Unit) {
        popUp()
    }


    fun fetchProjects()
    {
        storageService.fetchProjects { users ->
            uiState.value = uiState.value.copy(listFetched = true)

            users.forEach{it.projects.forEach{ project ->
                if(!projects.contains(project))
                {
                    projects.add(project)
                }
            }}
        }
    }
}