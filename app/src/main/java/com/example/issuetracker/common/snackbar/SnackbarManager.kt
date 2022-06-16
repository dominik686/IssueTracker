package com.example.issuetracker.common.snackbar

import androidx.annotation.StringRes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object SnackbarManager{
    private val message: MutableStateFlow<SnackbarMessage?> = MutableStateFlow(null)
    val snackbarMessage: StateFlow<SnackbarMessage?> get() = message.asStateFlow()

    fun showMessage(@StringRes messageResource: Int)
    {
        message.value = SnackbarMessage.ResourceSnackbar(messageResource)
    }

    fun showMessage(messageSnackBar: SnackbarMessage)
    {
        message.value = messageSnackBar
    }
}