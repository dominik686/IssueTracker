package com.example.issuetracker.common.snackbar

import androidx.annotation.StringRes
import androidx.compose.runtime.MutableState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object SnackbarManager{
    private val messages: MutableStateFlow<SnackbarMessage?> = MutableStateFlow(null)
    private val snackbarMessage: StateFlow<SnackbarMessage?> get() = messages.asStateFlow()

    fun showMessage(@StringRes message: Int)
    {
        messages.value = SnackbarMessage.ResourceSnackbar(message)
    }

    fun showMessage(message: SnackbarMessage)
    {
        messages.value = message
    }
}