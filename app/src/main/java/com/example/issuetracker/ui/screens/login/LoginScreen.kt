package com.example.issuetracker.ui.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.issuetracker.R
import com.example.issuetracker.common.composables.BasicButton
import com.example.issuetracker.common.composables.TextButton
import com.example.issuetracker.common.composables.EmailField
import com.example.issuetracker.common.composables.PasswordField
import com.example.issuetracker.common.extensions.basicButtonModifier
import com.example.issuetracker.common.extensions.fieldModifier
import com.example.issuetracker.common.extensions.spacer
import com.example.issuetracker.common.extensions.textButtonModifier


@Composable
fun LoginScreen(openAndPopUp: (String, String) -> Unit,
                modifier: Modifier = Modifier,
                viewModel: LoginViewModel = hiltViewModel())
{
    val uiState by viewModel.uiState



    Column(modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
       {

           EmailField(value = uiState.email , onNewValue = {viewModel.onEmailChange(it)}, modifier= Modifier.fieldModifier())
           PasswordField(value =uiState.password , onNewValue ={viewModel.onPasswordChange(it)}, modifier= Modifier.fieldModifier() )
           BasicButton(text = R.string.sign_in,modifier= Modifier.basicButtonModifier().fillMaxWidth()){viewModel.onSignInPressed()}
           TextButton(text = R.string.forgot_password,modifier= Modifier.textButtonModifier()) {viewModel.onForgotPasswordPressed()}
           TextButton(text = R.string.create_new_account,modifier= Modifier.textButtonModifier()) {viewModel.onCreateNewAccountPressed()}

       }
}
