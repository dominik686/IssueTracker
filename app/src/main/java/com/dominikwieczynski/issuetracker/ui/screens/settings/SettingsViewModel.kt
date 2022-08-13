package com.dominikwieczynski.issuetracker.ui.screens.settings

import androidx.compose.runtime.mutableStateOf
import com.dominikwieczynski.issuetracker.IssueTrackerViewModel
import com.dominikwieczynski.issuetracker.LOGIN_SCREEN
import com.dominikwieczynski.issuetracker.model.service.AccountService
import com.dominikwieczynski.issuetracker.model.service.LogService
import com.dominikwieczynski.issuetracker.model.service.impl.AccountServiceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(logService: LogService, accountService: AccountService)
    : IssueTrackerViewModel(logService)
{
    var uiState = mutableStateOf(SettingsUiState())

    fun onDeleteAccountPressed() {
        TODO("Not yet implemented")

    }

    fun onSignOutPressed(clearAndNavigate: (String) -> Unit) {
        clearAndNavigate(LOGIN_SCREEN)
    }

}