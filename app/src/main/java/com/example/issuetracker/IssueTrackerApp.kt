package com.example.issuetracker

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.issuetracker.ui.screens.login.LoginScreen
import com.example.issuetracker.ui.theme.IssueTrackerTheme

@ExperimentalMaterialApi
@Composable
fun IssueTrackerApp()
{

    IssueTrackerTheme {
        Surface(color = MaterialTheme.colors.background) {
            val appState = rememberAppState()
            
            Scaffold() {
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
) = remember(
    navHostController,
  )
{
    IssueTrackerAppState(navHostController)
}


@ExperimentalMaterialApi
fun NavGraphBuilder.issueTrackerGraph(appState : IssueTrackerAppState)
{
     composable(LOGIN_SCREEN){
         LoginScreen(openAndPopUp = {route, popUp -> appState.navigateAndPopUp(route, popUp)})
     }
}