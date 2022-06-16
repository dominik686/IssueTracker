package com.example.issuetracker.common.snackbar

import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource
import com.example.issuetracker.R.string as AppText

sealed class SnackbarMessage{
    class StringSnackbar(val message: String) : SnackbarMessage()
    class ResourceSnackbar(@StringRes val message: Int) : SnackbarMessage()

    companion object{
        fun SnackbarMessage.toMessage(resources: Resources): String
        {
            return when (this){
                is StringSnackbar -> this.message
                is ResourceSnackbar -> resources.getString(this.message)
            }
        }

        fun Throwable.toSnackBarMessage() : SnackbarMessage{
            val message = this.message.orEmpty()
            return if(message.isNotBlank()) StringSnackbar(message)
            else ResourceSnackbar(AppText.generic_error)
        }
    }
}