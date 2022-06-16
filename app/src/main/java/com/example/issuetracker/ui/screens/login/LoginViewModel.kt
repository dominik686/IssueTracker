package com.example.issuetracker.ui.screens.login

import androidx.compose.runtime.mutableStateOf
import com.example.issuetracker.IssueTrackerViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor()
    : IssueTrackerViewModel()
{
    var uiState = mutableStateOf(LoginUiState())

    fun onPasswordChange(password: String) {
        uiState.value = uiState.value.copy(password = password)
    }

    fun onEmailChange(email: String) {
      uiState.value = uiState.value.copy(email = email)

    }


}