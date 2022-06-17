package com.example.issuetracker.ui.screens.success

import androidx.compose.runtime.mutableStateOf
import com.example.issuetracker.IssueTrackerViewModel
import com.example.issuetracker.LOGIN_SCREEN
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SuccessViewModel @Inject constructor(): IssueTrackerViewModel()
{
    fun onAnimationFinish(popUpTo: (String) -> Unit) {
        popUpTo(LOGIN_SCREEN)
    }

    var uiState = mutableStateOf(SuccessScreenState())
}