package com.dominikwieczynski.issuetracker.ui.screens.add_project

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
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dominikwieczynski.issuetracker.R
import com.dominikwieczynski.issuetracker.R.string as AppText
import com.dominikwieczynski.issuetracker.common.composables.BasicButton
import com.dominikwieczynski.issuetracker.common.composables.BasicField
import com.dominikwieczynski.issuetracker.common.composables.NavigationIconToolbar
import com.dominikwieczynski.issuetracker.common.extensions.basicButtonModifier
import com.dominikwieczynski.issuetracker.common.extensions.fieldModifier
import com.dominikwieczynski.issuetracker.common.extensions.spacer

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalComposeUiApi
@Composable
fun AddProjectScreen(
    modifier: Modifier = Modifier,
    popUp: () -> Unit,
    viewModel: AddProjectViewModel = hiltViewModel()
) {
    val projectName = remember { mutableStateOf("Issue Tracker") }
    val projectDescription = remember { mutableStateOf("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus tristique, leo ac congue interdum, nunc mi euismod nunc, id lacinia nisl leo at eros. Proin congue, sapien et dignissim ullamcorper, leo lacus tincidunt nulla, laoreet ornare est orci sit amet quam. Nam facilisis ut turpis sit amet feugiat. Ut vel accumsan lorem, sed porta velit. Ut gravida id nulla vulputate consequat. Vivamus ipsum ipsum, faucibus id congue eu, viverra at ipsum. Aliquam ornare nunc ligula, a pretium ante ornare ac. Nulla dignissim tellus nec facilisis consectetur. Nunc elementum interdum enim. Mauris consectetur pellentesque libero consectetur molestie. Aliquam pellentesque ante vitae felis pretium, sed aliquam velit viverra. Suspendisse commodo faucibus enim, ullamcorper sollicitudin nibh mattis non. Vivamus venenatis metus sit amet dolor placerat luctus vitae et velit. Donec sagittis luctus congue. Nam at enim orci. Phasellus nec finibus nisi, quis aliquet mi.") }


    Scaffold(topBar = { NavigationIconToolbar(title = AppText.add_new_project, navigationIcon =Icons.Default.Close, backButtonPressed = {popUp()})}) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {

            Spacer(modifier = Modifier.spacer())
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            )
            {

                BasicField(
                    text = projectName.value,
                    onNewValue = { projectName.value = it },
                    modifier = Modifier.fieldModifier(),
                    imageVector = Icons.Filled.Label,
                    placeholderText = AppText.name
                )
                BasicField(
                    text = projectDescription.value,
                    onNewValue = { projectDescription.value = it },
                    modifier = Modifier.fieldModifier(),
                    imageVector = Icons.Filled.Description,
                    placeholderText = AppText.description
                )
                ProgrammingLanguagesChipGroup(onSelectionChange = {label ->
                    viewModel.onSelectionChange(label)})
                BasicButton(text = AppText.add, modifier = Modifier
                    .basicButtonModifier()
                    .fillMaxWidth(), action = {
                    viewModel.onAddPressed(projectName.value, projectDescription.value)
                    popUp()
                })

            }

        }
    }



}

@OptIn(ExperimentalMaterial3Api::class)
@Composable

private fun ProgrammingLanguagesChipGroup(onSelectionChange: (String) -> Unit)
{
    var labels = stringArrayResource(id = R.array.programming_languages_array)
    var selectedLabels  by  remember{ mutableStateOf(emptyList<String>())}
   // Log.d("AddProject", selectedLabels.toString())
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
                    selected = selectedLabels.contains(it),
                    onClick = {
                        selectedLabels = if(selectedLabels.contains(it)) {
                            val newList = selectedLabels.toMutableList()
                            newList.remove(it)
                            newList
                        } else {
                            val newList = selectedLabels.toMutableList()
                            newList.add(it)
                            newList

                        }
                        onSelectionChange(it)

                    },
                    label = { Text(it) },
                    selectedIcon = {Icon(imageVector = Icons.Outlined.Check, "Check icon")} )
            }
        }
    }
    Divider(Modifier.padding(top = 10.dp, bottom = 10.dp, start = 16.dp, end =16.dp))
}
