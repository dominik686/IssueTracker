package com.example.issuetracker.common.composables

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.issuetracker.R

@Composable
fun Banner(modifier: Modifier = Modifier)
{
    Text(text = stringResource(id = R.string.app_name) , style = MaterialTheme.typography.h4, modifier = modifier, textAlign = TextAlign.Center)
}