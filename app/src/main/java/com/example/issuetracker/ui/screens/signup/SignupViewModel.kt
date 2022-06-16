package com.example.issuetracker.ui.screens.signup

import androidx.compose.runtime.mutableStateOf
import com.example.issuetracker.IssueTrackerViewModel
import com.example.issuetracker.model.service.interfaces.AccountService
import com.example.issuetracker.ui.screens.login.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private var accountService: AccountService):  IssueTrackerViewModel()
{
    var uiState = mutableStateOf(SignUpUiState())

}