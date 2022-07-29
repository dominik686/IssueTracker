package com.dominikwieczynski.issuetracker.ui.screens.issue_list

import androidx.compose.runtime.mutableStateOf
import com.dominikwieczynski.issuetracker.IssueTrackerViewModel
import com.dominikwieczynski.issuetracker.model.service.AccountService
import com.dominikwieczynski.issuetracker.model.service.LogService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IssueListViewModel @Inject constructor(
    private var accountService: AccountService,
    private var logService: LogService
) : IssueTrackerViewModel() {
    var uiState = mutableStateOf(IssueListUiState())
}