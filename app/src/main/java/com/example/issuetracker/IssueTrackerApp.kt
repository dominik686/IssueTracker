package com.example.issuetracker

import android.content.res.Resources
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.issuetracker.common.snackbar.SnackbarManager
import com.example.issuetracker.ui.screens.login.LoginScreen
import com.example.issuetracker.ui.screens.signup.SignUpScreen
import com.example.issuetracker.ui.screens.success.SuccessScreen
import com.example.issuetracker.ui.theme.IssueTrackerTheme
import kotlinx.coroutines.CoroutineScope

@ExperimentalMaterialApi
@Composable
fun IssueTrackerApp()
{

    IssueTrackerTheme {
        Surface(color = MaterialTheme.colors.background) {
            val appState = rememberAppState()
            
            Scaffold(snackbarHost={
                SnackbarHost(hostState = it, modifier = Modifier.padding(), snackbar = {
                        snackbarData ->
                    Snackbar(snackbarData, contentColor = MaterialTheme.colors.onPrimary)  })
            }, scaffoldState = appState.scaffoldState) {
                NavHost(navController = appState.navHostController, startDestination = LOGIN_SCREEN,
                modifier = Modifier.padding(it)){
                    this.issueTrackerGraph(appState)
                }
                
            }
        }

    }
}

@Composable
fun rememberAppState(
    navHostController: NavHostController = rememberNavController(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    scaffoldState: ScaffoldState = rememberScaffoldState()

    ) = remember(
    navHostController, snackbarManager, resources, coroutineScope, scaffoldState
  )
{
    IssueTrackerAppState(navHostController, snackbarManager, resources, coroutineScope, scaffoldState)
}

@Composable
@ReadOnlyComposable
fun resources(): Resources
{
    LocalConfiguration.current
    return LocalContext.current.resources
}
@ExperimentalMaterialApi
fun NavGraphBuilder.issueTrackerGraph(appState : IssueTrackerAppState)
{
     composable(LOGIN_SCREEN){
         LoginScreen(openAndPopUp = {route, popUp -> appState.navigateAndPopUp(route, popUp)}, navigate = {route -> appState.navigate(route)})
     }
    composable(SIGN_UP_SCREEN){
        SignUpScreen(navigateAndPopUpTo = {route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }
    composable(SUCCESSFUL_ACCOUNT_CREATION_SCREEN){
        SuccessScreen(successMessage = R.string.sign_up_successful, popUpTo = {route -> appState.popUpTo(route)})
    }
}