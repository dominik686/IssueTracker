package com.dominikwieczynski.issuetracker.ui.screens.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.dominikwieczynski.issuetracker.IssueTrackerViewModel
import com.dominikwieczynski.issuetracker.PROJECT_LIST_SCREEN
import com.dominikwieczynski.issuetracker.SIGN_UP_SCREEN
import com.dominikwieczynski.issuetracker.common.extensions.isValidEmail
import com.dominikwieczynski.issuetracker.common.extensions.isValidPassword
import com.dominikwieczynski.issuetracker.common.snackbar.SnackbarManager
import com.dominikwieczynski.issuetracker.model.service.AccountService
import com.dominikwieczynski.issuetracker.model.service.LogService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.dominikwieczynski.issuetracker.R.string as AppText

@HiltViewModel
class LoginViewModel @Inject constructor(
    private var accountService: AccountService,
    private var logService: LogService)
    : IssueTrackerViewModel()
{
    var uiState = mutableStateOf(LoginUiState())

    fun onPasswordChange(password: String) {
        uiState.value = uiState.value.copy(password = password, areCredentialsCorrect = true)
    }

    fun onEmailChange(email: String) {
      uiState.value = uiState.value.copy(email = email, areCredentialsCorrect = true)

    }

    fun onSignInPressed(navigate: (String) -> Unit) {
        val email = uiState.value.email.trim()
        val password = uiState.value.password

        if(!email.isValidEmail()){
            SnackbarManager.showMessage(AppText.email_error)
            Log.d("LoginScreen", "Invalid email")
            return
        }
        if(!password.isValidPassword())
        {
            SnackbarManager.showMessage(AppText.password_error)
            Log.d("LoginScreen", "Invalid password")
            return
        }
        else
        {
            accountService.authenticate(uiState.value.email, uiState.value.password) { exception ->
                if(exception == null)
                {
                    Log.d("Firebase", "Login in successful")
                    navigate(PROJECT_LIST_SCREEN)
                }
                else {
                    Log.d("Firebase", "Login in NOT successful")
                    SnackbarManager.showMessage(AppText.incorrect_credentials)
                    uiState.value = uiState.component1().copy(areCredentialsCorrect = false)
                    logService.logNonFatalException(exception)
                }
            }
        }

    }

    fun onForgotPasswordPressed() {
      //  TODO("Not yet implemented")
    }

    fun onCreateNewAccountPressed(navigate: (String) -> Unit) {
        navigate(SIGN_UP_SCREEN)
    }


}