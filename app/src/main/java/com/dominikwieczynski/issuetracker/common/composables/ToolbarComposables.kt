package com.dominikwieczynski.issuetracker.common.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicToolbar(@StringRes title: Int) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(title)) },
        //backgroundColor = toolbarColor()
    )

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarWithSettings(
    @StringRes title: Int,
    settingsIcon: ImageVector,
    modifier: Modifier,
    onSettingsIconPressed: () -> Unit,
    filterIcon: ImageVector = Icons.Filled.FilterList,
    onFilterIconPressed: () -> Unit)
{
    CenterAlignedTopAppBar(
        title = { Text(stringResource(title))},
        actions ={
            Box(modifier = modifier) {
                IconButton(onClick = { onFilterIconPressed() }) {
                    Icon(imageVector = filterIcon, contentDescription = "Action")
                }
            }
            Box(modifier = modifier){
                IconButton(onClick = {onSettingsIconPressed()}) {
                    Icon(imageVector = settingsIcon, contentDescription = "Action")
                }
            }

        }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationIconToolbar(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    navigationIcon: ImageVector,
    backButtonPressed: () -> Unit )
{


    CenterAlignedTopAppBar(
        title = { Text(textAlign = TextAlign.Companion.Center, text = stringResource(title))
                },
      //  backgroundColor = toolbarColor(),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.surface),
        navigationIcon =
        {IconButton(
            modifier = modifier,
            onClick = {
            backButtonPressed()}, enabled = true) {
            Icon(imageVector = navigationIcon, contentDescription = "Back arrow icon",
                modifier = Modifier.clickable { backButtonPressed() }
            )
        }}
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IssueListToolbar(modifier: Modifier = Modifier, @StringRes title: Int, backButtonPressed: () -> Unit)
{
    CenterAlignedTopAppBar(modifier = modifier, title = { Text(stringResource(title))},
        navigationIcon = {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back arrow icon",
                modifier = Modifier.clickable{backButtonPressed()})
        }

    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackButtonToolbarWithSettingsAndFilter(
    @StringRes title: Int,
    settingsIcon: ImageVector = Icons.Filled.Settings,
    modifier: Modifier = Modifier,
    onSettingsIconPressed: () -> Unit,
    navigationIcon: ImageVector = Icons.Filled.ArrowBack,
    onBackButtonPressed: () -> Unit,
    filterIcon: ImageVector = Icons.Filled.FilterList,
    onFilterIconPressed: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(title)) },
        navigationIcon =
        {
            IconButton(
                modifier = modifier,
                onClick = {
                    onBackButtonPressed()
                }, enabled = true
            ) {
                Icon(imageVector = navigationIcon, contentDescription = "Back arrow icon",
                    modifier = Modifier.clickable { onBackButtonPressed() }
                )
            }
        },

        actions = {
            Box(modifier = modifier) {
                IconButton(onClick = { onFilterIconPressed() }) {
                    Icon(imageVector = filterIcon, contentDescription = "Action")
                }
            }
            Box(modifier = modifier) {
                IconButton(onClick = { onSettingsIconPressed() }) {
                    Icon(imageVector = settingsIcon, contentDescription = "Action")
                }
            }

        }
    )
}