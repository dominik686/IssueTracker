package com.example.issuetracker.ui.screens.signup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.issuetracker.R
import com.example.issuetracker.common.composables.*
import com.example.issuetracker.common.extensions.bannerModifier
import com.example.issuetracker.common.extensions.basicButtonModifier
import com.example.issuetracker.common.extensions.fieldModifier
import com.example.issuetracker.common.snackbar.SnackbarManager
import com.example.issuetracker.common.snackbar.SnackbarMessage
import com.example.issuetracker.R.string as AppText

@Composable
fun SignUpScreen(popUp: () -> Unit, navigateAndPopUpTo: (String, String) -> Unit,
                modifier: Modifier = Modifier,
                viewModel: SignUpViewModel = hiltViewModel()
)
{
    val uiState by viewModel.uiState

    BackArrowToolbar(title = R.string.sign_up, icon = Icons.Filled.ArrowBack, modifier = modifier, backArrowPressed = {viewModel.onBackArrowPressed(popUp)
    SnackbarManager.showMessage(SnackbarMessage.StringSnackbar("test button press"))
    })


    Column(modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {

        Banner(modifier = Modifier.bannerModifier())
        EmailField(value = uiState.email , onNewValue = {viewModel.onEmailChange(it)}, modifier= Modifier.fieldModifier())
        PasswordField(value =uiState.password , onNewValue ={viewModel.onPasswordChange(it)}, modifier= Modifier.fieldModifier() )
        RepeatPasswordField(value = uiState.repeatedPassword , onNewValue ={viewModel.onRepeatPasswordChange(it)}, modifier= Modifier.fieldModifier() )
        BasicButton(text = AppText.sign_up, modifier= Modifier
            .basicButtonModifier()
            .fillMaxWidth()){viewModel.onSignUpPressed(navigateAndPopUpTo)}
    }
}
