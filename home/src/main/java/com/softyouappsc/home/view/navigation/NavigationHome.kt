package com.softyouappsc.home.view.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.softyouappsc.home.view.components.HomeComponent

const val homeNavigationRoute = "home"

fun NavController.navigateToHomeGame(navOptions: NavOptions? = null) {
    this.navigate(homeNavigationRoute, navOptions)
}

fun NavGraphBuilder.navigateHomeVideoGame(onHomeClick: () -> Unit, onDetailClick: (Int) -> Unit) {
    composable(
        route = homeNavigationRoute,
    ) {
        HomeComponent(onHomeClick, onDetailClick)
    }
}