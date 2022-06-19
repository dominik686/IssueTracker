package com.example.issuetracker.ui.screens.project_list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.issuetracker.R
import com.example.issuetracker.common.composables.BackButtonToolbar
import com.example.issuetracker.common.composables.BackButtonToolbarWithEndAction
import com.example.issuetracker.common.composables.BasicButton
import com.example.issuetracker.common.composables.BasicFabButton
import com.example.issuetracker.model.ProjectsListModel

@Composable
fun ProjectListScreen(popUp: () -> Unit, viewModel: ProjectListViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState
    BasicFabButton(fabPosition = FabPosition.Center, onClick = { }, modifier = Modifier)
    {
        Icon(imageVector = Icons.Filled.AddCircle, contentDescription = "ADd new project")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BackButtonToolbar(
            title = R.string.toolbar_projects,
            backButtonPressed =
            { viewModel.onBackArrowPressed(popUp) },
            modifier = Modifier,
        )


        BasicButton(text = R.string.sign_in, action = {
            viewModel.addUser()
            viewModel.getProjects()
        })

        uiState.projects.forEach {
            TestListElement(it)
        }







    }

}
@Composable
fun TestListElement(project : ProjectsListModel)
{
    Text(project.name)
}