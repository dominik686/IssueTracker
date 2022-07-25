package com.dominikwieczynski.issuetracker.common.composables

import androidx.annotation.StringRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Banner(modifier: Modifier = Modifier, @StringRes text: Int)
{
    Text(text = stringResource(id = text) , style = MaterialTheme.typography.h4, modifier = modifier, textAlign = TextAlign.Center)
}