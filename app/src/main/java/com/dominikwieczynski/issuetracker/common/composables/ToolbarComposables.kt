package com.dominikwieczynski.issuetracker.common.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@Composable
private fun toolbarColor(darkTheme: Boolean = isSystemInDarkTheme()): Color
{
    //return if(darkTheme) MaterialTheme.colors.secondary else MaterialTheme.colors.primaryVariant
    return MaterialTheme.colors.primary
}
@Composable
fun BasicToolbar(@StringRes title: Int) {
    TopAppBar(
        title = { Text(stringResource(title)) },
        backgroundColor = toolbarColor()
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
    @StringRes title: Int,
    modifier: Modifier,
    backButtonPressed: () -> Unit )
{
    TopAppBar(
        title = { Text(stringResource(title))
                },
        backgroundColor = toolbarColor(),
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
fun BackButtonToolbarWithEndAction(
    @StringRes title: Int,
    modifier: Modifier,
    backButtonPressed: () -> Unit,
    endAction: () -> Unit,
    endActionIcon: ImageVector
)
{
    TopAppBar(
        title = { Text(stringResource(title))
        },
        backgroundColor = toolbarColor(),
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