package com.dominikwieczynski.issuetracker.ui.screens.add_issue

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Label
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dominikwieczynski.issuetracker.R
import com.dominikwieczynski.issuetracker.common.composables.BasicButton
import com.dominikwieczynski.issuetracker.common.composables.BasicField
import com.dominikwieczynski.issuetracker.common.composables.NavigationIconToolbar
import com.dominikwieczynski.issuetracker.common.extensions.basicButtonModifier
import com.dominikwieczynski.issuetracker.common.extensions.fieldModifier
import com.dominikwieczynski.issuetracker.common.extensions.spacer
import kotlin.random.Random

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AddIssueScreen(modifier: Modifier = Modifier, viewModel: AddIssueViewModel = hiltViewModel(), projectId : String, popUp:() -> Unit) {

    var uiState by remember {viewModel.uiState}

    Scaffold(topBar = {
        NavigationIconToolbar(title = R.string.add_new_issue, icon = Icons.Default.Close, backButtonPressed = {
            popUp()
        })
    }) { padding ->
        Spacer(modifier = Modifier.spacer())

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(padding)
        ) {

            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            )
            {
                BasicField(
                    //yooo also add a timestamp for issues
                    text = uiState.issue.name,
                    onNewValue = { viewModel.onNameChanged(name = it)},
                    modifier = Modifier.fieldModifier(),
                    imageVector = Icons.Filled.Label,
                    placeholderText = R.string.title
                )
                BasicField(
                    text = uiState.issue.description,
                    onNewValue = { viewModel.onDescriptionChanged(it)},
                    modifier = Modifier.fieldModifier(),
                    imageVector = Icons.Filled.Description,
                    placeholderText = R.string.description
                )
                LabelChipGroup()
                BasicButton(text = R.string.add, modifier = Modifier
                    .basicButtonModifier()
                    .fillMaxWidth(), action = {
                    viewModel.onAddPressed(projectId = projectId)
                })


            }

        }
    }

}




@OptIn(ExperimentalMaterial3Api::class)
@Composable

private fun LabelChipGroup()
{
    var enhancementSelected by remember { mutableStateOf(false) }
    var bugSelected by remember { mutableStateOf(false) }
    Divider(Modifier.padding(top = 10.dp, bottom = 10.dp, start = 16.dp, end =16.dp))

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
        horizontalAlignment = Alignment.Start,
    )
    {
        Text(
            modifier = Modifier.padding(start =16.dp),
            text = "Label",
            style = MaterialTheme.typography.titleMedium
        )
        Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.Top) {
            ElevatedFilterChip(
                modifier = Modifier.padding(start = 16.dp, end = 4.dp),
                selected = enhancementSelected,
                onClick = { enhancementSelected = !enhancementSelected },
                label = { Text("Enhancement") })
            ElevatedFilterChip(
                modifier = Modifier.padding(start = 4.dp),
                selected = bugSelected,
                onClick = { bugSelected = !bugSelected },
                label = { Text("Bug") })
        }
    }
    Divider(Modifier.padding(top = 10.dp, bottom = 10.dp, start = 16.dp, end =16.dp))


}