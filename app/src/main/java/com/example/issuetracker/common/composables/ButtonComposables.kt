package com.example.issuetracker.common.composables

import androidx.annotation.StringRes
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp

@Composable
fun TextButton(@StringRes text: Int, modifier: Modifier = Modifier, action: () -> Unit)
{
    TextButton(onClick = action, modifier = modifier) {
        Text(text = stringResource(id = text))
    }
}

@Composable
fun BasicButton(@StringRes text: Int, modifier: Modifier= Modifier, action: () -> Unit)
{
    Button(onClick = action, modifier = modifier, colors = ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary)) {
            Text(text = stringResource(id = text), fontSize = 16.sp)
        }
}

@Composable
fun BasicFabButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        content = content,
        backgroundColor = MaterialTheme.colors.primary
    )
}