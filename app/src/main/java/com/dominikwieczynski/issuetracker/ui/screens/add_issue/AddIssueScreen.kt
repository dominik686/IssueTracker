package com.dominikwieczynski.issuetracker.ui.screens.add_issue

import android.provider.Settings.Global.getString
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Label
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dominikwieczynski.issuetracker.R.string as AppText
import com.dominikwieczynski.issuetracker.common.composables.BasicButton
import com.dominikwieczynski.issuetracker.common.composables.BasicField
import com.dominikwieczynski.issuetracker.common.composables.NavigationIconToolbar
import com.dominikwieczynski.issuetracker.common.extensions.basicButtonModifier
import com.dominikwieczynski.issuetracker.common.extensions.fieldModifier
import com.dominikwieczynski.issuetracker.common.extensions.spacer
import com.dominikwieczynski.issuetracker.resources
import kotlin.random.Random

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AddIssueScreen(modifier: Modifier = Modifier, viewModel: AddIssueViewModel = hiltViewModel(), projectId : String, popUp:() -> Unit) {

    var uiState by remember {viewModel.uiState}

    Scaffold(topBar = {
        NavigationIconToolbar(title = AppText.add_new_issue, icon = Icons.Default.Close,
            backButtonPressed = { popUp() })
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
                    placeholderText = AppText.title
                )
                BasicField(
                    text = uiState.issue.description,
                    onNewValue = { viewModel.onDescriptionChanged(it)},
                    modifier = Modifier.fieldModifier(),
                    imageVector = Icons.Filled.Description,
                    placeholderText = AppText.description
                )
                LabelChipGroup(onSelectionChange = {label ->
                    viewModel.onSelectionChanged(label)

                })
                BasicButton(text = AppText.add, modifier = Modifier
                    .basicButtonModifier()
                    .fillMaxWidth(), action = {
                    if(viewModel.isLabelSelected())
                    {
                        viewModel.onAddPressed(projectId = projectId)
                        popUp()
                    }

                })


            }

        }
    }

}




@OptIn(ExperimentalMaterial3Api::class)
@Composable

private fun LabelChipGroup(onSelectionChange: (String) -> Unit)
{
    var labels = listOf<String>(stringResource(id = AppText.enhancement), stringResource(id=AppText.bug))
    var selectedLabel by remember{mutableStateOf("")}
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
        LazyRow(Modifier.padding(start = 16.dp, end = 16.dp), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.Top) {
            items(labels)
            {
                ElevatedFilterChip(
                    modifier = Modifier.padding(start = 4.dp, end = 4.dp),
                    selected = selectedLabel == it,
                    onClick = { selectedLabel = it; onSelectionChange(it) },
                    label = { Text(it) },
                    selectedIcon = {Icon(imageVector = Icons.Outlined.Check, "Check icon")} )
            }
        }
    }
    Divider(Modifier.padding(top = 10.dp, bottom = 10.dp, start = 16.dp, end =16.dp))


}