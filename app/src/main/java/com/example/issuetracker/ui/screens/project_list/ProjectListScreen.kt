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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.issuetracker.R
import com.example.issuetracker.common.composables.BackButtonToolbar
import com.example.issuetracker.common.composables.BackButtonToolbarWithEndAction
import com.example.issuetracker.common.composables.BasicButton
import com.example.issuetracker.common.composables.BasicFabButton
import com.example.issuetracker.common.snackbar.SnackbarManager
import com.example.issuetracker.common.snackbar.SnackbarMessage
import com.example.issuetracker.model.ProjectsListModel

@ExperimentalComposeUiApi
@Composable
fun ProjectListScreen(popUp: () -> Unit, viewModel: ProjectListViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState

    BasicFabButton(fabPosition = FabPosition.Center, onClick = {viewModel.fabPressed()
                                                               SnackbarManager.showMessage(SnackbarMessage.StringSnackbar("Test"))}, modifier = Modifier)
    {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add new project")
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

        if(uiState.dialogOpen)
        {
            AddNewProjectAlertDialog(modifier = Modifier.fillMaxSize())
        }




    }

}

@ExperimentalComposeUiApi
@Composable
fun AddNewProjectAlertDialog(
   // onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
)
{
    AlertDialog(modifier = modifier,
        onDismissRequest = {

            //openDialog.value = false
        },
        title = {
            Text(text = "Dialog Title")
        },
        text = {
            Text("Here is a text ")
        },
        confirmButton = {
            Button(

                onClick = {
                }) {
                Text("This is the Confirm Button")
            }
        },
        dismissButton = {
            Button(

                onClick = {
                }) {
                Text("This is the dismiss Button")
            }
        },
        properties = DialogProperties(
                usePlatformDefaultWidth = false
                ),)
}
@Composable
fun TestListElement(project : ProjectsListModel)
{
    Text(project.name)
}