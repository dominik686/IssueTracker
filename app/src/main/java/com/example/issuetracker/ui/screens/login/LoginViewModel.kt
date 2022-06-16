package com.example.issuetracker.ui.screens.login

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import com.example.issuetracker.IssueTrackerViewModel
import com.example.issuetracker.model.service.interfaces.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(var accountService: AccountService)
    : IssueTrackerViewModel()
{
    var uiState = mutableStateOf(LoginUiState())

    fun onPasswordChange(password: String) {
        uiState.value = uiState.value.copy(password = password)
    }

    fun onEmailChange(email: String) {
      uiState.value = uiState.value.copy(email = email)

    }

    fun onSignInPressed() {
    //    TODO
        accountService.authenticate(uiState.value.email,uiState.value.password) {
            if(it == null)
            {
                Log.d("Firebase", "Login in successful")
            }
           else {
               Log.d("Firebase", "Login in NOT successful")
           }
        }
    }

    fun onForgotPasswordPressed() {
      //  TODO("Not yet implemented")
    }

    fun onCreateNewAccountPressed() {
//        TODO("Not yet implemented")
    }


}