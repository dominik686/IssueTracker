package com.dominikwieczynski.issuetracker.common.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

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
fun EndActionToolBar(
    @StringRes title: Int,
    @DrawableRes endActionIcon: Int,
    modifier: Modifier,
    endAction: () -> Unit )
{
    TopAppBar(
        title = { Text(stringResource(title)) },
        backgroundColor = toolbarColor(),
        actions ={
            Box(modifier = modifier){
                IconButton(onClick = endAction) {
                    Icon(painter = painterResource(id = endActionIcon), contentDescription = "Action")
                }
            }
        }
    )
}
@Composable
fun BackButtonToolbar(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    backButtonPressed: () -> Unit )
{


    CenterAlignedTopAppBar(
        title = { Text(textAlign = TextAlign.Companion.Center, text = stringResource(title))
                },
      //  backgroundColor = toolbarColor(),
        navigationIcon =
        {IconButton(
            modifier = modifier,
            onClick = {
            backButtonPressed()}, enabled = true) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back arrow icon",
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