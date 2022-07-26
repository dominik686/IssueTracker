package com.dominikwieczynski.issuetracker.common.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.dominikwieczynski.issuetracker.R.string as AppText
@Composable
fun BasicField(
    modifier: Modifier = Modifier,
    @StringRes placeholderText: Int,
    imageVector: ImageVector,
    text : String,
    onNewValue: (String) -> Unit,
)
{
    OutlinedTextField(
        singleLine = true,
        modifier = modifier,
        value = text,
        onValueChange = {onNewValue(it)},
        placeholder = {Text(stringResource(id = placeholderText))},
        leadingIcon = {Icon(imageVector = imageVector, contentDescription = "")},
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),

        )

}

@Composable
fun EmailField(modifier: Modifier = Modifier, value: String, isError: Boolean = false, onNewValue: (String) -> Unit, )
{

    OutlinedTextField(
        singleLine = true,
        modifier = modifier,
        value = value,
        onValueChange = { onNewValue(it) },
        placeholder = { Text(stringResource(AppText.email)) },
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") },
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
        isError = isError,

        )
}

@Composable
fun PasswordField(modifier: Modifier = Modifier, value: String, isError: Boolean = false, onNewValue: (String) -> Unit, ) {
    PasswordField(modifier, value, isError, AppText.password, onNewValue, )
}

@Composable
fun RepeatPasswordField(    modifier: Modifier = Modifier,
                            value: String, isError: Boolean = false, onNewValue: (String) -> Unit) {
    PasswordField(modifier, value, isError,  AppText.repeat_password, onNewValue, )
}


@Composable
private fun PasswordField(
    modifier: Modifier = Modifier,
    value: String,
    isError: Boolean,
    @StringRes placeholder: Int,
    onNewValue: (String) -> Unit,

) {
    var isVisible by remember { mutableStateOf(false) }


    val visualTransformation = if (isVisible) VisualTransformation.None
    else PasswordVisualTransformation()

    OutlinedTextField(
        modifier = modifier,
        value = value,
        isError = isError,
        onValueChange = { onNewValue(it) },
        placeholder = { Text(text = stringResource(placeholder)) },
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Lock") },
        trailingIcon = {
            IconButton(onClick = { isVisible = !isVisible }) {
                if (isVisible)
                {
                    Icon(Icons.Filled.Visibility, "visibility on")
                }
                else
                {
                    Icon(Icons.Filled.VisibilityOff, "visibility off")
                }
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = visualTransformation
    )
}