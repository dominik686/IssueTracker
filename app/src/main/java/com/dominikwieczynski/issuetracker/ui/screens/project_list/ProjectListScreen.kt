package com.dominikwieczynski.issuetracker.ui.screens.project_list

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dominikwieczynski.issuetracker.ADD_PROJECT_SCREEN
import com.dominikwieczynski.issuetracker.ISSUE_LIST_SCREEN
import com.dominikwieczynski.issuetracker.common.composables.*
import com.dominikwieczynski.issuetracker.common.extensions.bannerModifier
import com.dominikwieczynski.issuetracker.model.Project
import com.dominikwieczynski.issuetracker.R.string as AppText
import androidx.compose.foundation.lazy.items

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalComposeUiApi
@Composable
fun ProjectListScreen(modifier: Modifier = Modifier, navigate: (String) -> Unit,
                      popUp: () -> Unit, viewModel: ProjectListViewModel = hiltViewModel()) {
    var uiState by remember { viewModel.uiState}
    var projects = viewModel.projects
    viewModel.fetchProjects()

    Scaffold(floatingActionButton = { BasicFabButton(onClick = {navigate(ADD_PROJECT_SCREEN)})
        {
          Icon(imageVector = Icons.Filled.Add, contentDescription = "Add new project")
        } }, topBar ={
        NavigationIconToolbar(
            title = AppText.toolbar_projects,
            backButtonPressed = { viewModel.onBackArrowPressed(popUp) },
            modifier = Modifier,
            icon= Icons.Default.ArrowBack
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



            if(projects.isEmpty() && uiState.listFetched)
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
        else {
                LazyColumn(contentPadding = padding) {
                    items(projects)
                    {
                        ProjectCard(it, navigate)
                    }
                }
            }
    }

    DisposableEffect(viewModel){
        viewModel.addProjectAddedListener()
        onDispose { viewModel.removeProjectAddedListener() }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectCard(project : Project, navigate: (String) -> Unit)
{

    ElevatedCard(
        onClick = {
            navigate(ISSUE_LIST_SCREEN + "/${project.id}")
        },
        elevation = CardDefaults.elevatedCardElevation(),
        colors = CardDefaults.elevatedCardColors(),
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
    ) {
        Box(Modifier.fillMaxSize()) {


                Text(text = project.name, style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .align(Alignment.TopStart)

            )
            LazyRow(Modifier
                .align(Alignment.TopEnd)
                .fillMaxWidth(0.5f)
                .padding(horizontal = 8.dp, vertical = 4.dp))
            {
                items(project.languages.size)
                {
                    Log.d("ProjectList", project.languages[it])
                    ProgrammingLanguagesCard(language = project.languages[it])
                }
            }

                Text(text = project.description, style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(top = 40.dp, start = 8.dp, end = 8.dp, bottom = 8.dp)
                        .align(Alignment.BottomStart))

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProgrammingLanguagesCard(modifier: Modifier = Modifier, language :String)
{
    OutlinedCard(
        elevation = CardDefaults.outlinedCardElevation(),
        colors = CardDefaults.outlinedCardColors(),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 4.dp))
    {
        Text(text = language, style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp))
    }
}