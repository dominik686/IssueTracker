package com.dominikwieczynski.issuetracker.ui.screens.add_issue

import androidx.compose.runtime.mutableStateOf
import com.dominikwieczynski.issuetracker.IssueTrackerViewModel
import com.dominikwieczynski.issuetracker.model.Issue
import com.dominikwieczynski.issuetracker.model.service.LogService
import com.dominikwieczynski.issuetracker.model.service.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class AddIssueViewModel @Inject constructor(
    val logService: LogService,
    val storageService: StorageService
) : IssueTrackerViewModel()
{
    var uiState = mutableStateOf(AddIssueUiState(Issue(name = "Bug nr. ${Random.nextInt()}", description = "Bug nr. ${Random.nextInt()}")))
    fun onAddPressed(projectId : String) {
        storageService.addIssue(
            Issue(name = uiState.value.issue.name, description = uiState.value.issue.description),
            projectId = projectId
        )

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