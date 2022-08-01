package com.dominikwieczynski.issuetracker.ui.screens.add_issue

import android.util.Log
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
import kotlin.random.Random

@HiltViewModel
class AddIssueViewModel @Inject constructor(
    val accountService: AccountService,
    val logService: LogService,
    val storageService: StorageService
) : IssueTrackerViewModel(logService)
{
    var uiState = mutableStateOf(AddIssueUiState(Issue(name = "Bug nr. ${Random.nextInt()}", description = "Bug nr. ${Random.nextInt()}")))

    fun isLabelSelected() : Boolean{
        return uiState.value.issue.label.isNotEmpty()
    }

    fun onAddPressed(projectId : String) {
        storageService.addIssue(
            uiState.value.issue,
            projectId = projectId
        )

    }

    fun onSelectionChanged(label: String)
    {
        uiState.value = uiState.value.copy(uiState.value.issue.copy(label = label))
    }
    fun onNameChanged(name: String)
    {
        uiState.value = uiState.value.copy(issue = uiState.value.issue.copy(name = name))
    }

    fun onDescriptionChanged(description: String)
    {
        uiState.value = uiState.value.copy(issue = uiState.value.issue.copy(description = description))

    }




}