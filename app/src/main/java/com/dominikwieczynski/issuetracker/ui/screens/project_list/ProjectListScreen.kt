package com.dominikwieczynski.issuetracker.ui.screens.project_list

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.outlined.Check
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.window.Dialog
import com.dominikwieczynski.issuetracker.R
import com.dominikwieczynski.issuetracker.SETTINGS_SCREEN
import com.dominikwieczynski.issuetracker.common.extensions.basicButtonModifier
import com.dominikwieczynski.issuetracker.common.extensions.fieldModifier
import com.dominikwieczynski.issuetracker.common.extensions.spacer
import com.dominikwieczynski.issuetracker.model.ProjectFilters
import com.dominikwieczynski.issuetracker.ui.screens.add_project.ProgrammingLanguagesChipGroup
import com.google.accompanist.flowlayout.FlowRow

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@ExperimentalComposeUiApi
@Composable
fun ProjectListScreen(modifier: Modifier = Modifier, navigate: (String) -> Unit,
                      popUp: () -> Unit, viewModel: ProjectListViewModel = hiltViewModel()) {
    var uiState by remember { viewModel.uiState}
    var projects = viewModel.projects
    var dialogShown = remember { mutableStateOf(false)}
   // viewModel.fetchProjects()
    var isScrollInProgress = remember {mutableStateOf(false)}
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {


            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                isScrollInProgress.value = true
                Log.d("ProjectList", "PreScroll: " + isScrollInProgress.value.toString())
                return super.onPreScroll(available, source)
            }


            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
               isScrollInProgress.value = false
                Log.d("ProjectList", "PostScroll: " +isScrollInProgress.value.toString())

                return super.onPostFling(consumed, available)
            }

        }
    }

        Scaffold(
            modifier = Modifier.nestedScroll(nestedScrollConnection),
            floatingActionButton = {

                AnimatedVisibility(
                    visible = !isScrollInProgress.value,
                    enter = scaleIn(animationSpec = tween(1000)),
                   exit = scaleOut(animationSpec = tween(1000))
                ) {
                    BasicFabButton(onClick = { navigate(ADD_PROJECT_SCREEN) })
                    {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add new project")
                    }
                }
            },
            topBar = {
                ToolbarWithSettings(
                    title = AppText.toolbar_projects,
                    onSettingsIconPressed = { navigate(SETTINGS_SCREEN) },
                    modifier = Modifier,
                    settingsIcon = Icons.Default.Settings,
                    onFilterIconPressed = { dialogShown.value = !dialogShown.value }
                )
            },
        )
        { padding ->

            if (!uiState.listFetched) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(fraction = 0.5f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    AmongUsLoadingAnimation(Modifier)
                }
            } else if (projects.isEmpty() && uiState.listFetched) {
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
            } else if (projects.isNotEmpty() && uiState.listFetched) {
                LazyColumn(contentPadding = padding) {
                    items(projects)
                    {
                        ProjectCard(
                            it,
                            onProjectPressed = { id -> viewModel.onProjectPressed(navigate, id) })
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProjectFilterDialog(onFilterPressed: (ProjectFilters) -> Unit, onDismissRequest: () -> Unit)
{
    // Show the dialog after pressing the button, make the design similiar to AddProject,
    // and make the button close the dialog and apply filter
    val projectName = remember { mutableStateOf("Issue Tracker") }
    val projectDescription = remember { mutableStateOf("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus tristique, leo ac congue interdum, nunc mi euismod nunc, id lacinia nisl leo at eros. Proin congue, sapien et dignissim ullamcorper, leo lacus tincidunt nulla, laoreet ornare est orci sit amet quam. Nam facilisis ut turpis sit amet feugiat. Ut vel accumsan lorem, sed porta velit. Ut gravida id nulla vulputate consequat. Vivamus ipsum ipsum, faucibus id congue eu, viverra at ipsum. Aliquam ornare nunc ligula, a pretium ante ornare ac. Nulla dignissim tellus nec facilisis consectetur. Nunc elementum interdum enim. Mauris consectetur pellentesque libero consectetur molestie. Aliquam pellentesque ante vitae felis pretium, sed aliquam velit viverra. Suspendisse commodo faucibus enim, ullamcorper sollicitudin nibh mattis non. Vivamus venenatis metus sit amet dolor placerat luctus vitae et velit. Donec sagittis luctus congue. Nam at enim orci. Phasellus nec finibus nisi, quis aliquet mi.") }


    Scaffold(topBar = { NavigationIconToolbar(title = AppText.project_filters, navigationIcon =Icons.Default.Close, backButtonPressed = {onDismissRequest()})}) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {

            Spacer(modifier = Modifier.spacer())
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            )
            {

                ProgrammingLanguagesChipGroup(onSelectionChange = {label ->
                    })
                BasicButton(text = AppText.apply, modifier = Modifier
                    .basicButtonModifier()
                    .fillMaxWidth(), action = {
                })
            }
        }
    }





}