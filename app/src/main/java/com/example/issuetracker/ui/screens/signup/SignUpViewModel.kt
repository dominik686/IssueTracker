package com.example.issuetracker.ui.screens.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.issuetracker.*
import com.example.issuetracker.common.extensions.isValidEmail
import com.example.issuetracker.common.extensions.isValidPassword
import com.example.issuetracker.common.snackbar.SnackbarManager
import com.example.issuetracker.model.service.interfaces.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.issuetracker.R.string as AppText

@HiltViewModel
class SignUpViewModel @Inject constructor(private var accountService: AccountService):  IssueTrackerViewModel()
{
    var uiState = mutableStateOf(SignUpUiState())

    fun onPasswordChange(password: String) {
        uiState.value = uiState.value.copy(password = password)
    }

    fun onEmailChange(email: String) {
        uiState.value = uiState.value.copy(email = email)

    }

    fun onSignUpPressed(navigateAndPopUpTo: (String, String) -> Unit) {
        //Remove this later
        navigateAndPopUpTo(SUCCESSFUL_ACCOUNT_CREATION_SCREEN, SIGN_UP_SCREEN)

        val email = uiState.value.email.trim()
        val password = uiState.value.password
        val repeatedPassword = uiState.value.repeatedPassword
        if(!email.isValidEmail()){
            SnackbarManager.showMessage(R.string.email_error)
            Log.d("SignUpScreen", "Invalid email")
            return

        }
        if(!password.isValidPassword())
        {
             SnackbarManager.showMessage(AppText.password_error)
            Log.d("SignUpScreen", "Invalid password")
            return
        }
        if(!repeatedPassword.isValidPassword())
        {
            SnackbarManager.showMessage(AppText.repeat_password_error)
            return
        }
        if(repeatedPassword != password)
        {
            SnackbarManager.showMessage(AppText.matching_password_error)
            return
        }
        else
        {
            accountService.createUserWithEmailAndPassword(email, password) {
                if(it == null)
                {
                    Log.d("Firebase", "Account creation successful")
                    navigateAndPopUpTo(SUCCESSFUL_ACCOUNT_CREATION_SCREEN, SIGN_UP_SCREEN)
                }
                else {
                    Log.d("Firebase", "Account creation NOT successful")
                }
            }
        }    }

    fun onRepeatPasswordChange(repeatedPassword: String) {
        uiState.value = uiState.value.copy(repeatedPassword = repeatedPassword )
    }


}