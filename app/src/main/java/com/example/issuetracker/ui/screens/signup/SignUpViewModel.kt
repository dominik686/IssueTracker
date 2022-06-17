package com.example.issuetracker.ui.screens.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.issuetracker.IssueTrackerViewModel
import com.example.issuetracker.R
import com.example.issuetracker.common.extensions.isValidEmail
import com.example.issuetracker.common.extensions.isValidPassword
import com.example.issuetracker.common.snackbar.SnackbarManager
import com.example.issuetracker.model.service.interfaces.AccountService
import com.example.issuetracker.ui.screens.login.LoginUiState
import com.google.firebase.auth.ktx.auth
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

    fun onSignUpPressed() {
        val email = uiState.value.email.trim()
        val password = uiState.value.password
        val repeatedPassword = uiState.value.repeatedPassword
        if(!email.isValidEmail()){
            SnackbarManager.showMessage(R.string.email_error)
            Log.d("SignUpScreen", "Invalid email")

        }
        if(!password.isValidPassword())
        {
             SnackbarManager.showMessage(AppText.password_error)
            Log.d("SignUpScreen", "Invalid password")

        }
        if(!repeatedPassword.isValidPassword())
        {
            SnackbarManager.showMessage(AppText.repeat_password_error)
        }
        else
        {
            accountService.createUserWithEmailAndPassword(email, password) {
                if(it == null)
                {
                    Log.d("Firebase", "Account creation successful")
                }
                else {
                    Log.d("Firebase", "Account creation  NOT successful")
                }
            }
        }    }

    fun onRepeatPasswordChange(repeatedPassword: String) {
        uiState.value = uiState.value.copy(repeatedPassword = repeatedPassword )
    }


}