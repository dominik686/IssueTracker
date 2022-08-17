package com.dominikwieczynski.issuetracker.ui.screens.settings

import androidx.compose.runtime.mutableStateOf
import com.dominikwieczynski.issuetracker.IssueTrackerViewModel
import com.dominikwieczynski.issuetracker.LOGIN_SCREEN
import com.dominikwieczynski.issuetracker.model.service.AccountService
import com.dominikwieczynski.issuetracker.model.service.LogService
import com.dominikwieczynski.issuetracker.model.service.StorageService
import com.dominikwieczynski.issuetracker.model.service.impl.AccountServiceImpl
import com.dominikwieczynski.issuetracker.model.service.impl.StorageServiceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(logService: LogService, val accountService: AccountService,
                                            val storageService: StorageService)
    : IssueTrackerViewModel(logService)
{
    var uiState = mutableStateOf(SettingsUiState())
    fun setAuthenticationListener(clearAndNavigate: (String) -> Unit)
    {
        accountService.setAuthenticationListener({clearAndNavigate(LOGIN_SCREEN) }, {})
    }

    // Maybe its fai
    fun onDeleteAccountPressed(clearAndNavigate: (String) -> Unit) {
        val uid : String = accountService.getUserId()
        accountService.deleteAccount {
            if (it == null) {
                storageService.deleteAllUserData(UID = uid, onError = {onError(it)}, onResult = { exception ->
                    if (exception == null) {
                        // delete account should sign out as well (according to docs)
                       // accountService.signOut()
                        clearAndNavigate(LOGIN_SCREEN)

                    }
                    else if(exception != null)
                    {
                        onError(exception)
                    }
                })
            }
            else if(it != null)
            {
                onError(it)
            }
        }

    }

    fun onSignOutPressed(clearAndNavigate: (String) -> Unit) {
        accountService.signOut()
        clearAndNavigate(LOGIN_SCREEN)
    }

}