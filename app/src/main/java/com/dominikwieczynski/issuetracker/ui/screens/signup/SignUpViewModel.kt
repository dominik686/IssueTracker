package com.dominikwieczynski.issuetracker.ui.screens.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.dominikwieczynski.issuetracker.*
import com.dominikwieczynski.issuetracker.common.extensions.isValidEmail
import com.dominikwieczynski.issuetracker.common.extensions.isValidPassword
import com.dominikwieczynski.issuetracker.common.extensions.isValidUsername
import com.dominikwieczynski.issuetracker.common.snackbar.SnackbarManager
import com.dominikwieczynski.issuetracker.model.service.AccountService
import com.dominikwieczynski.issuetracker.model.service.LogService
import com.dominikwieczynski.issuetracker.model.service.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.dominikwieczynski.issuetracker.R.string as AppText

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private var accountService: AccountService,
    private var logService: LogService,
    private var storageService: StorageService
):  IssueTrackerViewModel()
{
    var uiState = mutableStateOf(SignUpUiState())

    fun onPasswordChange(password: String) {
        uiState.value = uiState.value.copy(password = password)
    }

    fun onEmailChange(email: String) {
        uiState.value = uiState.value.copy(email = email)

    }
    fun onUsernameChange(username: String)
    {
        uiState.value = uiState.value.copy(username = username)

    }

    fun onSignUpPressed(navigateAndPopUpTo: (String, String) -> Unit) {

        val email = uiState.value.email.trim()
        val password = uiState.value.password
        val repeatedPassword = uiState.value.repeatedPassword
        val username = uiState.value.username.trim()

        if(!username.isValidUsername())
        {
            SnackbarManager.showMessage(R.string.username_error)
            Log.d("SignUpScreen", "Invalid username")
            return
        }
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
            storageService.checkIfUsernameExists(username = username, onResult = {
                if(!it){
                    accountService.createUserWithEmailAndPassword(email, password) {authenticationException ->
                        if(authenticationException == null)
                        {
                            storageService.addUser(username = username, onSuccess = {

                            },
                                onFailure = {
                                    SnackbarManager.showMessage(AppText.sign_up_failed)

                                })
                            Log.d("SignUpScreen", "Account creation successful")
                            navigateAndPopUpTo(SUCCESSFUL_ACCOUNT_CREATION_SCREEN, SIGN_UP_SCREEN)
                        }
                        else {
                            Log.d("SignUpScreen", "Account creation NOT successful")
                            logService.logNonFatalException(authenticationException)
                            SnackbarManager.showMessage(AppText.error_occurred)

                        }
                    }
                }
                else{
                    SnackbarManager.showMessage(AppText.username_exists_error)

                }

            })
        }    }

    fun onRepeatPasswordChange(repeatedPassword: String) {
        uiState.value = uiState.value.copy(repeatedPassword = repeatedPassword )
    }

    fun onBackArrowPressed(popUp: () -> Unit) {
        popUp()
    }


}