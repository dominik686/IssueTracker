package com.dominikwieczynski.issuetracker.ui.screens.issue_list

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dominikwieczynski.issuetracker.R
import com.dominikwieczynski.issuetracker.common.composables.BackButtonToolbarWithEndAction
import com.dominikwieczynski.issuetracker.common.composables.BasicFabButton
import com.dominikwieczynski.issuetracker.common.composables.IssueListToolbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IssueListScreen(modifier: Modifier = Modifier, popUp: () -> Unit, projectId : String, viewModel: IssueListViewModel = hiltViewModel())
{


    Scaffold(modifier = Modifier, topBar = {
        IssueListToolbar(
            title = R.string.toolbar_issues,
            backButtonPressed = popUp,)
    },
        floatingActionButton = { BasicFabButton(onClick = {}){Icon(imageVector = Icons.Default.Add, contentDescription = "Add issue")}
        }) {

    }
}