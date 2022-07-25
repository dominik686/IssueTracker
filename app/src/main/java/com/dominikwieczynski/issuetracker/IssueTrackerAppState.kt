package com.dominikwieczynski.issuetracker

import android.content.res.Resources
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController
import com.dominikwieczynski.issuetracker.common.snackbar.SnackbarManager
import com.dominikwieczynski.issuetracker.common.snackbar.SnackbarMessage.Companion.toMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@Stable
class IssueTrackerAppState(
    val navHostController: NavHostController,
    private val snackbarManager: SnackbarManager,
    private val resources: Resources,
     coroutineScope: CoroutineScope,
    val scaffoldState: ScaffoldState
) {

    init {
        coroutineScope.launch {
            snackbarManager.snackbarMessage.filterNotNull().collect{
                val text = it.toMessage(resources)
                scaffoldState.snackbarHostState.showSnackbar(text)
            }
        }
    }
    fun popUp()
    {
     navHostController.popBackStack()
    }

    fun popUpTo(route: String)
    {
        navHostController.popBackStack(route = route, inclusive = false, saveState = false)
    }
    fun navigate(route: String)
    {
      navHostController.navigate(route){ launchSingleTop = true }
    }
    fun navigateAndPopUp(route: String, popUpTo: String)
    {
        navHostController.navigate(route){
            launchSingleTop = true
            popUpTo(popUpTo){inclusive = true}
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