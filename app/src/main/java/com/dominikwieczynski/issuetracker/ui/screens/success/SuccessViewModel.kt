package com.dominikwieczynski.issuetracker.ui.screens.success

import androidx.compose.runtime.mutableStateOf
import com.dominikwieczynski.issuetracker.IssueTrackerViewModel
import com.dominikwieczynski.issuetracker.LOGIN_SCREEN
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