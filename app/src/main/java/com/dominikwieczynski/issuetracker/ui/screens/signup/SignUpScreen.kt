package com.dominikwieczynski.issuetracker.ui.screens.signup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dominikwieczynski.issuetracker.common.composables.*
import com.dominikwieczynski.issuetracker.common.extensions.*
import com.dominikwieczynski.issuetracker.R.string as AppText

@Composable
fun SignUpScreen(popUp: () -> Unit, navigateAndPopUpTo: (String, String) -> Unit,
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
    )
    {

        NavigationIconToolbar(title = AppText.sign_up,  modifier = Modifier, backButtonPressed = {viewModel.onBackArrowPressed(popUp)}, navigationIcon= Icons.Default.ArrowBack)



        Spacer(modifier = Modifier.signUpScreenSpacer())


        Banner(modifier = Modifier.bannerModifier(), AppText.app_name)
        BasicField(text = uiState.username , onNewValue = {viewModel.onUsernameChange(it)},
            modifier= Modifier.fieldModifier(), imageVector = Icons.Filled.Person, placeholderText = AppText.username)
        EmailField(value = uiState.email , onNewValue = {viewModel.onEmailChange(it)}, modifier= Modifier.fieldModifier())
        PasswordField(value =uiState.password , onNewValue ={viewModel.onPasswordChange(it)}, modifier= Modifier.fieldModifier() )
        RepeatPasswordField(value = uiState.repeatedPassword , onNewValue ={viewModel.onRepeatPasswordChange(it)}, modifier= Modifier.fieldModifier() )
        BasicButton(text = AppText.sign_up, modifier= Modifier
            .basicButtonModifier()
            .fillMaxWidth()){viewModel.onSignUpPressed(navigateAndPopUpTo)}


    }
}
