package com.dominikwieczynski.issuetracker.ui.screens.issue_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.dominikwieczynski.issuetracker.IssueTrackerViewModel
import com.dominikwieczynski.issuetracker.model.Issue
import com.dominikwieczynski.issuetracker.model.service.AccountService
import com.dominikwieczynski.issuetracker.model.service.LogService
import com.dominikwieczynski.issuetracker.model.service.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IssueListViewModel @Inject constructor(
    private val accountService: AccountService,
    private val storageService: StorageService,
    private val logService: LogService
) : IssueTrackerViewModel(logService) {
    var uiState = mutableStateOf(IssueListUiState())

    fun addListener(){
        viewModelScope.launch(showErrorExceptionHandler){
            storageService.addListener(accountService.getUserId(), ::onDocumentEvent, ::onError)
        }
    }

    fun removeListener()
    {
        viewModelScope.launch { showErrorExceptionHandler
        storageService.removeListener()
        }
    }


    private fun onDocumentEvent(wasIssueAdded: Boolean, issue: Issue) {

        if(wasIssueAdded)
        {
            var newList = uiState.value.issues.toMutableList()
            newList.add(issue)
            uiState.value = uiState.value.copy(issues = newList)
        }
    }



    fun addIssue(name: String, description: String, projectId: String) {
        storageService.addIssue(Issue(name = name, description = description), projectId = projectId)
    }
    fun fetchIssues(projectId: String)
    {
        storageService.fetchIssues(projectId = projectId) {
            uiState.value = uiState.value.copy(issues = it, areIssuesFetched = true)
        }
    }
}