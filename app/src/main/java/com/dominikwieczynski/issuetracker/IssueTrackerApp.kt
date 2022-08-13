package com.dominikwieczynski.issuetracker

import android.content.res.Resources
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.dominikwieczynski.issuetracker.common.animation.TransitionAnimations
import com.dominikwieczynski.issuetracker.common.snackbar.SnackbarManager
import com.dominikwieczynski.issuetracker.model.service.impl.AccountServiceImpl
import com.dominikwieczynski.issuetracker.theme.IssueTrackerTheme
import com.dominikwieczynski.issuetracker.ui.screens.add_issue.AddIssueScreen
import com.dominikwieczynski.issuetracker.ui.screens.add_project.AddProjectScreen
import com.dominikwieczynski.issuetracker.ui.screens.issue_list.IssueListScreen
import com.dominikwieczynski.issuetracker.ui.screens.login.LoginScreen
import com.dominikwieczynski.issuetracker.ui.screens.project_list.ProjectListScreen
import com.dominikwieczynski.issuetracker.ui.screens.settings.SettingsScreen
import com.dominikwieczynski.issuetracker.ui.screens.signup.SignUpScreen
import com.dominikwieczynski.issuetracker.ui.screens.success.SuccessScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun IssueTrackerApp() {
    IssueTrackerTheme {
        Surface(color = MaterialTheme.colors.background) {
            val appState = rememberAppState()

            Scaffold(
                snackbarHost = {
                    SnackbarHost(
                        hostState = it,
                        modifier = Modifier.padding(),
                        snackbar = { snackbarData ->
                            Snackbar(snackbarData, contentColor = MaterialTheme.colors.onPrimary)
                        })
                }, scaffoldState = appState.scaffoldState
            ) {

                if(AccountServiceImpl().isUserLoggedIn())
                {
                    AnimatedNavHost(
                        navController = appState.navHostController,
                        startDestination = PROJECT_LIST_SCREEN,
                        modifier = Modifier.padding(it)
                    ) {
                        this.issueTrackerGraph(appState)
                    }
                }
                else{
                    AnimatedNavHost(
                        navController = appState.navHostController,
                        startDestination = LOGIN_SCREEN,
                        modifier = Modifier.padding(it)
                    ) {
                        this.issueTrackerGraph(appState)
                    }
                }

            }


        }

    }
}

@ExperimentalAnimationApi
@Composable
fun rememberAppState(
    navHostController: NavHostController = rememberAnimatedNavController(),
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
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
fun NavGraphBuilder.issueTrackerGraph(appState : IssueTrackerAppState)
{
     composable(route = LOGIN_SCREEN,
         exitTransition = { TransitionAnimations.defaultExitTransition },
         popEnterTransition = { TransitionAnimations.defaultPopEnterAnimation}){
         LoginScreen(navigate = {route -> appState.navigate(route)})
     }
    composable(SIGN_UP_SCREEN,
        exitTransition = { TransitionAnimations.defaultExitTransition },
        enterTransition   = {TransitionAnimations.defaultEnterTransition },
        popExitTransition = { TransitionAnimations.defaultPopExitTransition},
        popEnterTransition = { TransitionAnimations.defaultPopEnterAnimation})
        {
        SignUpScreen(popUp = {appState.popUp()}, navigateAndPopUpTo = {route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }
    composable(SUCCESSFUL_ACCOUNT_CREATION_SCREEN,
        exitTransition = { TransitionAnimations.defaultExitTransition },
        enterTransition   = {TransitionAnimations.defaultEnterTransition },
        popExitTransition = { TransitionAnimations.defaultPopExitTransition},
        popEnterTransition = { TransitionAnimations.defaultPopEnterAnimation}){
        SuccessScreen(successMessage = R.string.sign_up_successful, popUpTo = {route -> appState.popUpTo(route)})
    }
    composable(PROJECT_LIST_SCREEN,
        exitTransition = { TransitionAnimations.defaultExitTransition },
        enterTransition   = {TransitionAnimations.defaultEnterTransition },
        popExitTransition = { TransitionAnimations.defaultPopExitTransition},
        popEnterTransition = { TransitionAnimations.defaultPopEnterAnimation})
    {

        ProjectListScreen(popUp = { appState.popUp() }, navigate = { route -> appState.navigate(route)})
    }
    composable(route = "$ISSUE_LIST_SCREEN/{projectId}",
        arguments = listOf(navArgument("projectId"){type = NavType.Companion.StringType}),
        exitTransition = { TransitionAnimations.defaultExitTransition },
        enterTransition   = {TransitionAnimations.defaultEnterTransition },
        popExitTransition = { TransitionAnimations.defaultPopExitTransition},
        popEnterTransition = { TransitionAnimations.defaultPopEnterAnimation})
    { backstackEntry ->

       var projectId = backstackEntry.arguments?.get("projectId") as String
        IssueListScreen(popUp = {appState.popUp()}, navigate = {route -> appState.navigate(route)}, projectId = projectId)
    }
    composable(route = "$ADD_ISSUE_SCREEN/{projectId}",
        arguments = listOf(navArgument("projectId"){type = NavType.Companion.StringType}),
        exitTransition = { TransitionAnimations.defaultExitTransition },
        enterTransition   = {TransitionAnimations.defaultEnterTransition },
        popExitTransition = { TransitionAnimations.defaultPopExitTransition},
        popEnterTransition = { TransitionAnimations.defaultPopEnterAnimation})
    { backstackEntry ->
        var projectId = backstackEntry.arguments?.get("projectId") as String

        AddIssueScreen(popUp = { appState.popUp() }, projectId = projectId)
    }
    composable(route= ADD_PROJECT_SCREEN,
        exitTransition = { TransitionAnimations.defaultExitTransition },
        enterTransition   = {TransitionAnimations.defaultEnterTransition },
        popExitTransition = { TransitionAnimations.defaultPopExitTransition},
        popEnterTransition = { TransitionAnimations.defaultPopEnterAnimation})
    {
        AddProjectScreen(popUp = {appState.popUp()}, )
    }
    composable(route = SETTINGS_SCREEN,
        exitTransition = { TransitionAnimations.defaultExitTransition },
        enterTransition   = {TransitionAnimations.defaultEnterTransition },
        popExitTransition = { TransitionAnimations.defaultPopExitTransition},
        popEnterTransition = { TransitionAnimations.defaultPopEnterAnimation}){

        SettingsScreen(popUp = {appState.popUp()}, clearAndNavigate = {appState.clearAndNavigate(it)})
    }
}