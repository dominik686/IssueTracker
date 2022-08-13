package com.dominikwieczynski.issuetracker.common.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
private fun toolbarColor(darkTheme: Boolean = isSystemInDarkTheme()): Color
{
    return if(darkTheme) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary
   // return MaterialTheme.colors.primary
}
@Composable
fun BasicToolbar(@StringRes title: Int) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(title)) },
        //backgroundColor = toolbarColor()
    )

}
@Composable
fun ProjectListToolbar(
    @StringRes title: Int,
     endActionIcon: ImageVector,
    modifier: Modifier,
    endAction: () -> Unit )
{
    var menuExpanded by remember{mutableStateOf(false)}
    CenterAlignedTopAppBar(
        title = { Text(stringResource(title)) },
        actions ={
            Box(modifier = modifier){
                IconButton(onClick = {menuExpanded = !menuExpanded}) {
                    Icon(imageVector = endActionIcon, contentDescription = "Action")
                }
            }
            AnimatedVisibility(modifier= Modifier.padding(top = 50.dp),visible = menuExpanded) {
                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false }
                ) {
                    DropdownMenuItem(onClick = { /*TODO*/ }, leadingIcon = {
                        Icon(Icons.Filled.Refresh, "")
                    }, text = { Text(text = "d") })
                    DropdownMenuItem(
                        onClick = { /*TODO*/ },
                        leadingIcon = { Icon(Icons.Filled.Call, "") },
                        text = { Text(text = "d") })
                }
            }
        }
    )
}
@Composable
fun NavigationIconToolbar(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    icon: ImageVector,
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
            Icon(imageVector = icon, contentDescription = "Back arrow icon",
                modifier = Modifier.clickable { backButtonPressed() }
            )
        }}
    )
}
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


@Composable
fun BackButtonToolbarWithEndAction(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    backButtonPressed: () -> Unit,
    endAction: () -> Unit,
    endActionIcon: ImageVector
)
{
    CenterAlignedTopAppBar(
        title = { Text(stringResource(title))
        },
      // backgroundColor = toolbarColor(),
        navigationIcon =
        {IconButton(
            modifier = modifier,
            onClick = {
                backButtonPressed()}, enabled = true) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back arrow icon",
                modifier = Modifier.clickable { backButtonPressed() }
            )
        }},
        actions = {
            IconButton(onClick = endAction) {
                Icon(imageVector = endActionIcon, contentDescription = "Action")
            }
        }
    )
}