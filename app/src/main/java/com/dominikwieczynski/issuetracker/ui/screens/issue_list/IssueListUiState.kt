package com.dominikwieczynski.issuetracker.ui.screens.issue_list

import com.dominikwieczynski.issuetracker.model.Issue

data class IssueListUiState(var areIssuesFetched : Boolean = false, var isAddIssueDialogOpen : Boolean = false, var issues: List<Issue> =
    emptyList()
) {
}