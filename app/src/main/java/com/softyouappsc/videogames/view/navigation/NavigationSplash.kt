package com.softyouappsc.videogames.view.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.softyouappsc.videogames.view.components.SplashScreen

const val splashNavigationRoute = "splash"

fun NavGraphBuilder.splashVideoGame(navController: NavHostController) {
    composable(
        route = splashNavigationRoute
    ) {
        SplashScreen(navController)
    }

}