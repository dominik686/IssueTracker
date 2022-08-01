package com.dominikwieczynski.issuetracker

import androidx.lifecycle.ViewModel
import com.dominikwieczynski.issuetracker.common.snackbar.SnackbarManager
import com.dominikwieczynski.issuetracker.common.snackbar.SnackbarMessage.Companion.toSnackBarMessage
import com.dominikwieczynski.issuetracker.model.service.LogService
import kotlinx.coroutines.CoroutineExceptionHandler

open class IssueTrackerViewModel(private val logService : LogService) : ViewModel(){
    open val showErrorExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        onError(throwable)
    }

    open fun onError(error: Throwable)
    {
        SnackbarManager.showMessage(error.toSnackBarMessage())
        logService.logNonFatalException(error)
    }
}