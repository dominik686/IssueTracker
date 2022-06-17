package com.example.issuetracker.ui.screens.signup

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
import com.example.issuetracker.common.composables.*
import com.example.issuetracker.common.extensions.basicButtonModifier
import com.example.issuetracker.common.extensions.fieldModifier
import com.example.issuetracker.R.string as AppText

@Composable
fun SignUpScreen(popBack: () -> Unit,
                modifier: Modifier = Modifier,
                viewModel: SignUpViewModel = hiltViewModel()
)
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
        RepeatPasswordField(value =uiState.repeatedPassword , onNewValue ={viewModel.onRepeatPasswordChange(it)}, modifier= Modifier.fieldModifier() )
        BasicButton(text = AppText.sign_up, modifier= Modifier
            .basicButtonModifier()
            .fillMaxWidth()){viewModel.onSignUpPressed()}
    }
}
