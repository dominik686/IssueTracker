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
import com.example.issuetracker.common.composables.*
import com.example.issuetracker.common.extensions.bannerModifier
import com.example.issuetracker.common.extensions.basicButtonModifier
import com.example.issuetracker.common.extensions.fieldModifier
import com.example.issuetracker.common.extensions.textButtonModifier


@Composable
fun LoginScreen(navigate: (String) -> Unit,
                modifier: Modifier = Modifier,
                viewModel: LoginViewModel = hiltViewModel())
{
    val uiState by viewModel.uiState


    BasicToolbar(title = R.string.sign_in)

    Column(modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
       {

           Banner(Modifier.bannerModifier())
           // Replaced purely for debugging
           EmailField(value = uiState.email , onNewValue = {viewModel.onEmailChange(it)}, modifier= Modifier.fieldModifier())
           PasswordField(value =uiState.password , onNewValue ={viewModel.onPasswordChange(it)}, modifier= Modifier.fieldModifier() )


           BasicButton(text = R.string.sign_in,modifier= Modifier
               .basicButtonModifier()
               .fillMaxWidth()){viewModel.onSignInPressed(navigate)}
           TextButton(text = R.string.forgot_password,modifier= Modifier.textButtonModifier()) {viewModel.onForgotPasswordPressed()}
           TextButton(text = R.string.not_registered,modifier= Modifier.textButtonModifier()) {viewModel.onCreateNewAccountPressed(navigate = navigate)}
       }
}
