package com.example.issuetracker.ui.screens.project_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ProjectListScreen(viewModel: ProjectListViewModel = hiltViewModel())
{
    val uiState by viewModel.uiState


}