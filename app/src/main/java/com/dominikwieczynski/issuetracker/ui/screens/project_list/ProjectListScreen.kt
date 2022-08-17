package com.dominikwieczynski.issuetracker.ui.screens.project_list

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import com.dominikwieczynski.issuetracker.common.composables.*
import com.dominikwieczynski.issuetracker.common.extensions.bannerModifier
import com.dominikwieczynski.issuetracker.model.Project
import com.dominikwieczynski.issuetracker.R.string as AppText
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.Velocity
import com.dominikwieczynski.issuetracker.SETTINGS_SCREEN

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalComposeUiApi
@Composable
fun ProjectListScreen(modifier: Modifier = Modifier, navigate: (String) -> Unit,
                      popUp: () -> Unit, viewModel: ProjectListViewModel = hiltViewModel()) {
    var uiState by remember { viewModel.uiState}
    var projects = viewModel.projects
   // viewModel.fetchProjects()

    var isScrollInProgress = remember {mutableStateOf(false)}
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {


            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                isScrollInProgress.value = true
                return super.onPreScroll(available, source)
            }

            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
               isScrollInProgress.value = false

                return super.onPostFling(consumed, available)
            }

        }
    }
    Scaffold(modifier = Modifier.nestedScroll(nestedScrollConnection),
        floatingActionButton = {

                AnimatedVisibility(
                    visible = !isScrollInProgress.value,
                    enter = fadeIn(animationSpec = tween(1000)),
                    exit = fadeOut(animationSpec = tween(1000))
                ) {
                    BasicFabButton(onClick = {navigate(ADD_PROJECT_SCREEN) })
                    {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add new project")
                    }
                }
             }, topBar ={
        ToolbarWithSettings(
            title = AppText.toolbar_projects,
            onSettingsIconPressed = { navigate(SETTINGS_SCREEN)},
            modifier = Modifier,
            settingsIcon = Icons.Default.Settings
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



            if(projects.isEmpty() && uiState.listFetched) {
                Column(
                    modifier = modifier
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
                        ProjectCard(it, onProjectPressed = { id -> viewModel.onProjectPressed(navigate, id)})
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
fun ProjectCard(project : Project, onProjectPressed: (String) -> Unit)
{

    ElevatedCard(
        onClick = {
            onProjectPressed(project.id)
        },
        elevation = CardDefaults.elevatedCardElevation(),
        colors = CardDefaults.elevatedCardColors(),
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(start = 16.dp, end = 16.dp, top = 6.dp, bottom = 6.dp)
    ) {
        Box(Modifier.fillMaxSize()) {


                Text(text = project.name, style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .align(Alignment.TopStart)

            )
            LazyRow(
                Modifier
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