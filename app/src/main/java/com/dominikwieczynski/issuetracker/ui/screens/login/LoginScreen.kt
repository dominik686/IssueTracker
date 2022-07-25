package com.dominikwieczynski.issuetracker.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dominikwieczynski.issuetracker.R.string as AppText
import com.dominikwieczynski.issuetracker.common.composables.*
import com.dominikwieczynski.issuetracker.common.extensions.bannerModifier
import com.dominikwieczynski.issuetracker.common.extensions.basicButtonModifier
import com.dominikwieczynski.issuetracker.common.extensions.fieldModifier
import com.dominikwieczynski.issuetracker.common.extensions.textButtonModifier
@Composable
fun LoginScreen(navigate: (String) -> Unit,
                modifier: Modifier = Modifier,
                viewModel: LoginViewModel = hiltViewModel())
{
    val uiState by viewModel.uiState


    BasicToolbar(title = AppText.sign_in)

    Column(modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
       {

           Banner(Modifier.bannerModifier(), AppText.app_name)
           EmailField(value = uiState.email , onNewValue = {viewModel.onEmailChange(it)}, modifier= Modifier.fieldModifier())
           PasswordField(value =uiState.password , onNewValue ={viewModel.onPasswordChange(it)}, modifier= Modifier.fieldModifier() )


           BasicButton(text = AppText.sign_in,modifier= Modifier
               .basicButtonModifier()
               .fillMaxWidth()){viewModel.onSignInPressed(navigate)}
           TextButton(text = AppText.forgot_password,modifier= Modifier.textButtonModifier()) {viewModel.onForgotPasswordPressed()}
           TextButton(text = AppText.not_registered,modifier= Modifier.textButtonModifier()) {viewModel.onCreateNewAccountPressed(navigate = navigate)}
       }
}
