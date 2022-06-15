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

}