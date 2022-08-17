package com.dominikwieczynski.issuetracker.ui.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.dominikwieczynski.issuetracker.common.composables.*
import com.dominikwieczynski.issuetracker.R.string as AppText
import com.dominikwieczynski.issuetracker.common.extensions.card

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel(),
                   clearAndNavigate: (String) -> Unit,
                   popUp: () -> Unit, )
{
    viewModel.setAuthenticationListener(clearAndNavigate)
    Scaffold(topBar = {
        NavigationIconToolbar(
            title = AppText.settings, navigationIcon = Icons.Default.ArrowBack,
            backButtonPressed = {popUp()}
        )
    }, )
        { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                SignOutCard {
                    viewModel.onSignOutPressed(clearAndNavigate)
                }
                DeleteMyAccountCard { viewModel.onDeleteAccountPressed(clearAndNavigate)}
                /*
                if (uiState.isAnonymousAccount) {
                    RegularCardEditor(AppText.sign_in, AppIcon.ic_sign_in, "", Modifier.card()) {
                        viewModel.onLoginClick(openScreen)
                    }

                    RegularCardEditor(AppText.create_account, AppIcon.ic_create_account, "", Modifier.card()) {
                        viewModel.onSignUpClick(openScreen)
                    }
                }
                }
                else {
                 */
                    //SignOutCard { viewModel.onSignOutClick(restartApp) }
                   // DeleteMyAccountCard { viewModel.onDeleteMyAccountClick(restartApp) }
               // }
            }
    }
}
@ExperimentalMaterialApi
@Composable
private fun SignOutCard(signOut: () -> Unit) {
    var showWarningDialog by remember { mutableStateOf(false) }

    RegularCardEditor(AppText.sign_out, Icons.Filled.ExitToApp, "", Modifier.card()) {
        showWarningDialog = true
    }

    if(showWarningDialog) {
        AlertDialog(
            title = { Text(stringResource(AppText.sign_out_title)) },
            text = { Text(stringResource(AppText.sign_out_description)) },
            dismissButton = { DialogCancelButton(AppText.cancel) { showWarningDialog = false } },
            confirmButton = {
                DialogConfirmButton(AppText.sign_out) {
                    signOut()
                    showWarningDialog = false
                }
            },
            onDismissRequest = { showWarningDialog = false }
        )
    }
}
@ExperimentalMaterialApi
@Composable
private fun DeleteMyAccountCard(deleteMyAccount: () -> Unit) {
    var showWarningDialog by remember { mutableStateOf(false) }

    DangerousCardEditor(AppText.delete_my_account, Icons.Filled.Delete, "", Modifier.card()) {
        showWarningDialog = true
    }

    if(showWarningDialog) {
        AlertDialog(
            title = { Text(stringResource(AppText.delete_account_title)) },
            text = { Text(stringResource(AppText.delete_account_description)) },
            dismissButton = { DialogCancelButton(AppText.cancel) { showWarningDialog = false } },
            confirmButton = {
                DialogConfirmButton(AppText.delete_my_account) {
                    deleteMyAccount()
                    showWarningDialog = false
                }
            },
            onDismissRequest = { showWarningDialog = false }
        )
    }
}