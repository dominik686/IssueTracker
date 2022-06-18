package com.example.issuetracker.ui.screens.project_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.issuetracker.R
import com.example.issuetracker.common.composables.BasicToolbar

@Composable
fun ProjectListScreen(viewModel: ProjectListViewModel = hiltViewModel())
{
    val uiState by viewModel.uiState
    BasicToolbar(title = R.string.toolbar_projects)


    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center ) {
        
    }
}