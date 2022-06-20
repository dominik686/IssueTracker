package com.example.issuetracker.ui.screens.project_list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Label
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.issuetracker.R
import com.example.issuetracker.common.composables.*
import com.example.issuetracker.common.extensions.*
import com.example.issuetracker.model.ProjectPublic
import com.example.issuetracker.theme.Gray300
import com.example.issuetracker.theme.Gray700
import com.example.issuetracker.theme.Gray900

@ExperimentalComposeUiApi
@Composable
fun ProjectListScreen(popUp: () -> Unit, viewModel: ProjectListViewModel = hiltViewModel()) {
    var uiState by remember { viewModel.uiState}
    viewModel.getProjects()

    Scaffold(floatingActionButton = { BasicFabButton(onClick = {viewModel.openDialog()})
        {
          Icon(imageVector = Icons.Filled.Add, contentDescription = "Add new project")
        } }, topBar ={
        BackButtonToolbar(
        title = R.string.toolbar_projects,
        backButtonPressed =
        { viewModel.onBackArrowPressed(popUp) },
        modifier = Modifier,
    )
                },  )
    { padding ->
        LazyColumn(contentPadding = padding){

            items(uiState.projects)
            {
                TestListElement(it)
            }



    }

        if(uiState.dialogOpen)
        {
            AddNewProjectAlertDialog(
                modifier = Modifier.fillMaxSize(),
                onDismissRequest = {viewModel.closeDialog()},
                onAddPressed = {name, description ->
                    viewModel.onAddPressed(name, description)
                    viewModel.closeDialog()
                    viewModel.updateProjects(name, description)
                }
            )
        }






    }

}


@ExperimentalComposeUiApi
@Composable
private fun AddNewProjectAlertDialog(
    modifier: Modifier = Modifier,
    viewModel: ProjectListViewModel = hiltViewModel(),
    onAddPressed:(String, String) -> Unit,
    onDismissRequest: () -> Unit
    )
{
    val projectName =  remember{mutableStateOf("issue")}
    val projectDescription = remember{mutableStateOf(" tracker")}


    Dialog(
        onDismissRequest = {
                onDismissRequest()

           // viewModel.closeDialog()
        },

        properties = DialogProperties(
                usePlatformDefaultWidth = false
                ),
        content = {
            Box(
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
                        IconButton(onClick = {
                            //viewModel.closeDialog()
                            onDismissRequest()

                        }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = null
                            )
                        }
                    }


                    Banner(modifier = Modifier.bannerModifier(), R.string.add_new_project)
                    BasicField(text = projectName.value , onNewValue = {projectName.value = it}, modifier= Modifier.fieldModifier(), imageVector = Icons.Filled.Label, placeholderText = R.string.name)
                    BasicField(text = projectDescription.value , onNewValue = {projectDescription.value = it}, modifier= Modifier.fieldModifier(), imageVector = Icons.Filled.Description, placeholderText = R.string.description)
                    BasicButton(text = R.string.add, modifier= Modifier
                        .basicButtonModifier()
                        .fillMaxWidth(), action = {
                        onAddPressed(projectName.value, projectDescription.value)
                    })


                }

            }
        }
    )
}

@Composable
fun TestListElement(project : ProjectPublic)
{
    Box(modifier = Modifier
        .fillMaxWidth()
        //.height(100.dp)
        .wrapContentHeight()
        .padding(16.dp) // margin
        .clip(RoundedCornerShape(12.dp))
        .background(Gray900))
    {
        Column(Modifier.padding(8.dp)) {
            Text("Project name:", color = Gray300)
            Text(text = project.name, color = Gray300)
            Text("Description:", color = Gray300)
            Text(text = project.description, color = Gray300)

        }

    }}