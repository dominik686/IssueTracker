package com.dominikwieczynski.issuetracker.ui.screens.issue_list

import androidx.compose.runtime.mutableStateOf
import com.dominikwieczynski.issuetracker.IssueTrackerViewModel
import com.dominikwieczynski.issuetracker.model.Issue
import com.dominikwieczynski.issuetracker.model.service.LogService
import com.dominikwieczynski.issuetracker.model.service.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IssueListViewModel @Inject constructor(
    private var storageService: StorageService,
    private var logService: LogService
) : IssueTrackerViewModel() {
    var uiState = mutableStateOf(IssueListUiState())

    fun openDialog()
    {
        uiState.value = uiState.value.copy(isAddIssueDialogOpen = true)
    }

    fun closeDialog() {
        uiState.value = uiState.value.copy(isAddIssueDialogOpen = false)
    }

    fun addIssue(name: String, description: String, projectId: String) {
        storageService.addIssue(Issue(name = name, description = description), projectId = projectId)
        closeDialog()
    }
    fun fetchIssues(projectId: String)
    {
        storageService.fetchIssues(projectId = projectId) {
            uiState.value = uiState.value.copy(issues = it, areIssuesFetched = true)
        }
    }
}