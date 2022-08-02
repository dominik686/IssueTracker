package com.dominikwieczynski.issuetracker.ui.screens.issue_list

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.dominikwieczynski.issuetracker.IssueTrackerViewModel
import com.dominikwieczynski.issuetracker.model.Issue
import com.dominikwieczynski.issuetracker.model.service.LogService
import com.dominikwieczynski.issuetracker.model.service.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IssueListViewModel @Inject constructor(
    private val storageService: StorageService,
    private val logService: LogService
) : IssueTrackerViewModel(logService) {
    var uiState = mutableStateOf(IssueListUiState())
    var issues = mutableStateListOf<Issue>()
        private set

    fun addIssueAddedListener(projectId: String){
        viewModelScope.launch(showErrorExceptionHandler){
            storageService.addIssueAddedListener(projectId, ::onDocumentEvent, ::onError)
            Log.d("IssueScreen", "ADd listener")

        }
    }

    fun removeIssueAddedListener()
    {
        viewModelScope.launch { showErrorExceptionHandler
        storageService.removeIssueAddedListener()
        }
    }


    private fun onDocumentEvent(wasIssueAdded: Boolean, issue: Issue) {

        if(wasIssueAdded)
        {
           // Log.d("IssueScreen", "DocumentEvent, was issue added $wasIssueAdded")
            if(!issues.contains(issue))
            {
                issues.add(issue)
            }
        }
    }



    fun addIssue(name: String, description: String, projectId: String) {
        storageService.addIssue(Issue(name = name, description = description), projectId = projectId)
    }
    fun fetchIssues(projectId: String)
    {
        storageService.fetchIssues(projectId = projectId) {
            //uiState.value = uiState.value.copy(issues = it, areIssuesFetched = true)
            uiState.value = uiState.value.copy(areIssuesFetched = true)

        }
    }
}