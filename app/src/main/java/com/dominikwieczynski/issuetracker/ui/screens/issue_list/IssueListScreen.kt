package com.dominikwieczynski.issuetracker.ui.screens.issue_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dominikwieczynski.issuetracker.common.composables.*
import com.dominikwieczynski.issuetracker.model.Issue
import com.dominikwieczynski.issuetracker.R.string as AppText
import androidx.compose.foundation.lazy.items
import com.dominikwieczynski.issuetracker.ADD_ISSUE_SCREEN


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IssueListScreen(modifier: Modifier = Modifier, navigate: (String) -> Unit, popUp: () -> Unit, projectId : String, viewModel: IssueListViewModel = hiltViewModel()) {

    var uiState by remember { viewModel.uiState }
    var issues  = viewModel.issues
    viewModel.fetchIssues(projectId = projectId)

    Scaffold(modifier = Modifier, topBar = {
        IssueListToolbar(
            title = AppText.toolbar_issues,
            backButtonPressed = popUp,
        )
    },
        floatingActionButton = {
            BasicFabButton(onClick = { navigate("$ADD_ISSUE_SCREEN/$projectId") }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add issue"
                )
            }
        }) { padding ->

        if (!uiState.areIssuesFetched) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AmongUsLoadingAnimation()
            }
        } else {

            LazyColumn(Modifier.padding(paddingValues = padding)) {
               // items(uiState.issues)
                items(issues)
                { issue ->
                    IssueCard(issue = issue, navigate = {})
                }
            }

        }
    }

    DisposableEffect(viewModel){
        viewModel.addIssueAddedListener(projectId)
        onDispose { viewModel.removeIssueAddedListener() }
    }
}





@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun IssueCard(issue : Issue, navigate: (String) -> Unit)
{

    ElevatedCard(
        onClick = {
            
        },
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
                Row {
                    Text(text = issue.name, style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(start = 4.dp, end = 4.dp)
                    )
                    Text(text = issue.label, style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(start = 4.dp, end = 4.dp))
                }
                Text(text = issue.description, style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 4.dp, end = 4.dp))

            }
       }
    }
}