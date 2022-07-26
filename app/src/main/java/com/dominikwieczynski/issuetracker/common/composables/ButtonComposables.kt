package com.dominikwieczynski.issuetracker.common.composables

import androidx.annotation.StringRes
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp

@Composable
fun TextButton(modifier: Modifier = Modifier, @StringRes text: Int,  action: () -> Unit)
{
    TextButton(onClick = action, modifier = modifier) {
        Text(text = stringResource(id = text))
    }
}

@Composable
fun BasicButton(modifier: Modifier= Modifier, @StringRes text: Int, action: () -> Unit)
{
    var buttonPressed = remember { mutableStateOf(false)}
    val transition = updateTransition(targetState =buttonPressed, label = "")

    val scale: Float by transition.animateFloat(
        transitionSpec = { spring(stiffness  = 900f)}, label = ""
    ){ state ->

        if(state.value)
        {
            0.95f
        }
        else{
            1f
        }

    }
    Button(onClick = action, modifier = modifier, colors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary),
    ) {
            Text(text = stringResource(id = text), fontSize = 16.sp)
        }
}

@Composable
fun BasicFabButton(
    modifier: Modifier= Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        content = content,
        containerColor = MaterialTheme.colorScheme.primary
    )
}