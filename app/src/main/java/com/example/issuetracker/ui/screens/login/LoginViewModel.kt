package com.example.issuetracker.ui.screens.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.issuetracker.IssueTrackerViewModel
import com.example.issuetracker.PROJECT_LIST_SCREEN
import com.example.issuetracker.SIGN_UP_SCREEN
import com.example.issuetracker.common.extensions.isValidEmail
import com.example.issuetracker.common.extensions.isValidPassword
import com.example.issuetracker.common.snackbar.SnackbarManager
import com.example.issuetracker.model.service.AccountService
import com.example.issuetracker.model.service.LogService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.issuetracker.R.string as AppText

@HiltViewModel
class LoginViewModel @Inject constructor(
    private var accountService: AccountService,
    private var logService: LogService)
    : IssueTrackerViewModel()
{
    var uiState = mutableStateOf(LoginUiState())

    fun onPasswordChange(password: String) {
        uiState.value = uiState.value.copy(password = password)
    }

    fun onEmailChange(email: String) {
      uiState.value = uiState.value.copy(email = email)

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
          //  SnackbarManager.showMessage(AppText.password_error)
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
                    // Shake the fields and tell the user their credentials are incorrect
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