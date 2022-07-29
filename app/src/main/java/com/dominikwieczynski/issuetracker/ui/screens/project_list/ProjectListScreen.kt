package com.dominikwieczynski.issuetracker.ui.screens.project_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Label
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.dominikwieczynski.issuetracker.ISSUE_LIST_SCREEN
import com.dominikwieczynski.issuetracker.common.composables.*
import com.dominikwieczynski.issuetracker.common.extensions.bannerModifier
import com.dominikwieczynski.issuetracker.common.extensions.basicButtonModifier
import com.dominikwieczynski.issuetracker.common.extensions.fieldModifier
import com.dominikwieczynski.issuetracker.common.extensions.spacer
import com.dominikwieczynski.issuetracker.model.ProjectPublic
import com.dominikwieczynski.issuetracker.R.string as AppText

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalComposeUiApi
@Composable
fun ProjectListScreen(modifier: Modifier = Modifier, navigate: (String) -> Unit,
                      popUp: () -> Unit, viewModel: ProjectListViewModel = hiltViewModel()) {
    var uiState by remember { viewModel.uiState}
    viewModel.getProjects()

    Scaffold(floatingActionButton = { BasicFabButton(onClick = {viewModel.openDialog()})
        {
          Icon(imageVector = Icons.Filled.Add, contentDescription = "Add new project")
        } }, topBar ={
        BackButtonToolbar(
        title = AppText.toolbar_projects,
        backButtonPressed =
        { viewModel.onBackArrowPressed(popUp) },
        modifier = Modifier,
    )
                },  )
    { padding ->
        if(!uiState.listFetched)
        {
            Column(modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.5f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AmongUsLoadingAnimation(Modifier)
            }
        }



            if(uiState.projects.isEmpty() && uiState.listFetched)
            {
            Column(modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Banner(text = AppText.no_projects_message, modifier = Modifier.bannerModifier())
            }
        }
        else
        {
        LazyColumn(contentPadding = padding) {
            items(uiState.projects)
            {
                ProjectCard(it, navigate)
            }
        }






    }

       if(uiState.dialogOpen)
        {
            AddNewProjectAlertDialog(
                modifier = Modifier.fillMaxSize(),
                onAddPressed = {name, description ->
                    viewModel.onAddPressed(name, description)
                    viewModel.closeDialog()
                    viewModel.updateProjects(name, description)
                }
            ) { viewModel.closeDialog() }
        }
    }
}


@ExperimentalComposeUiApi
@Composable
private fun AddNewProjectAlertDialog(
    modifier: Modifier = Modifier,
    onAddPressed: (String, String) -> Unit,
    onDismissRequest: () -> Unit
)
{
    val projectName =  remember{mutableStateOf("Issue")}
    val projectDescription = remember{mutableStateOf("Tracker")}


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
                    .background(MaterialTheme.colorScheme.background)) {

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


                    Banner(modifier = Modifier.bannerModifier(), AppText.add_new_project)
                    BasicField(text = projectName.value , onNewValue = {projectName.value = it},
                        modifier= Modifier.fieldModifier(), imageVector = Icons.Filled.Label, placeholderText = AppText.name)
                    BasicField(text = projectDescription.value , onNewValue = {projectDescription.value = it},
                        modifier= Modifier.fieldModifier(), imageVector = Icons.Filled.Description, placeholderText = AppText.description)
                    BasicButton(text = AppText.add, modifier= Modifier
                        .basicButtonModifier()
                        .fillMaxWidth(), action = {
                        onAddPressed(projectName.value, projectDescription.value)
                    })


                }

            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectCard(project : ProjectPublic, navigate: (String) -> Unit)
{

    ElevatedCard(
        onClick = { navigate(ISSUE_LIST_SCREEN + "/${project.id}") },
         elevation = CardDefaults.elevatedCardElevation(),
        colors = CardDefaults.elevatedCardColors(),
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(8.dp)
    ) {
        Box(Modifier.fillMaxSize()) {
            Column(Modifier.padding(8.dp)) {
              //  Text("Name:")
                Text(text = project.name, style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(start = 4.dp, end = 4.dp)
                )
                Text(text = project.description, style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 4.dp, end = 4.dp))

            }
        }
    }

    /*
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
        }
     */


}