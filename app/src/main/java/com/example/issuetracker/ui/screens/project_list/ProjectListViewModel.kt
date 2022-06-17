package com.example.issuetracker.ui.screens.project_list

import androidx.compose.runtime.mutableStateOf
import com.example.issuetracker.IssueTrackerViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProjectListViewModel @Inject constructor(): IssueTrackerViewModel()
{
    val uiState = mutableStateOf(ProjectListUiState())
}