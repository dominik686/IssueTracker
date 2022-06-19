package com.example.issuetracker.ui.screens.project_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Label
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.issuetracker.R
import com.example.issuetracker.common.composables.*
import com.example.issuetracker.common.extensions.*
import com.example.issuetracker.model.ProjectPublic

@ExperimentalComposeUiApi
@Composable
fun ProjectListScreen(popUp: () -> Unit, viewModel: ProjectListViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState

    BasicFabButton(fabPosition = FabPosition.Center, onClick = {viewModel.openDialog()})
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
            AddNewProjectAlertDialog(modifier = Modifier.fillMaxSize(),
                onDismissRequest = { viewModel.closeDialog() }
            )
        }




    }

}

@ExperimentalComposeUiApi
@Composable
fun AddNewProjectAlertDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
)
{
    Dialog(
        onDismissRequest = {
                           onDismissRequest()
        },

        properties = DialogProperties(
                usePlatformDefaultWidth = false
                ),
        content = {
            Box(//contentAlignment = Alignment.TopStart,
                modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)) {

                Spacer(modifier = Modifier.spacer())
                
                Column(modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                )
                {
                    Column(modifier = modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                        horizontalAlignment = Alignment.Start,
                    )
                    {
                        IconButton(onClick = { onDismissRequest() }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = null
                            )
                        }
                    }


                    Banner(modifier = Modifier.bannerModifier(), R.string.add_new_project)
                    BasicField(text = "" , onNewValue = {}, modifier= Modifier.fieldModifier(), imageVector = Icons.Filled.Label, placeholderText = R.string.name)
                    BasicField(text = "" , onNewValue = {}, modifier= Modifier.fieldModifier(), imageVector = Icons.Filled.Description, placeholderText = R.string.description)
                    BasicButton(text = R.string.add, modifier= Modifier
                        .basicButtonModifier()
                        .fillMaxWidth()){}


                }

            }
        }
    )
}
@ExperimentalComposeUiApi
@Composable
fun AddNewProjectDialog(
    // onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
)
{

}
@Composable
fun TestListElement(project : ProjectPublic)
{
    Text(project.name)
}