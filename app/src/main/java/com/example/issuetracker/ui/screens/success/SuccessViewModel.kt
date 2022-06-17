package com.example.issuetracker.ui.screens.success

import androidx.compose.runtime.mutableStateOf
import com.example.issuetracker.IssueTrackerViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SuccessViewModel @Inject constructor(): IssueTrackerViewModel()
{
    var uiState = mutableStateOf(SuccessScreenState())
}