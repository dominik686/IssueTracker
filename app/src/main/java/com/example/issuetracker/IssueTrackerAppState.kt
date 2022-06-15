package com.example.issuetracker

import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController

@Stable
class IssueTrackerAppState(
    val navHostController: NavHostController,
) {

    fun popUp()
    {
     navHostController.popBackStack()
    }

    fun navigate(route: String)
    {
      navHostController.navigate(route){ launchSingleTop = true }
    }
    fun navigateAndPopUp(route: String, popUp: String)
    {
        navHostController.navigate(route){
            launchSingleTop = true
            popUpTo(popUp){inclusive = true}
        }
    }
    fun clearAndNavigate(route: String)
    {
        navHostController.navigate(route){
            launchSingleTop = true
            popUpTo(0){inclusive = true}
        }
    }

}