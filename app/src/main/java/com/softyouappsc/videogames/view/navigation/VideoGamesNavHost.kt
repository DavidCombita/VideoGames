package com.softyouappsc.videogames.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.softyouappsc.detail.view.navigation.detailVideoGame
import com.softyouappsc.home.view.navigation.navigateHomeVideoGame
import com.softyouappsc.videogames.view.components.splashNavigationRoute

@Composable
fun VideoGamesNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = splashNavigationRoute,
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        detailVideoGame ( onDetailClick = navController::navigateToDetail,
            onBackClick = navController::popBackStack)
        navigateHomeVideoGame (
            onHomeClick = navController::navigateToHome,
            onDetailClick = navController::navigateToDetail)
        splashVideoGame (navController)
    }
}

fun NavController.navigateToDetail(videoGameId: Int, isDB: Boolean) {
    this.navigate("detail_game/$videoGameId/$isDB") {
        launchSingleTop = true
    }
}

fun NavController.navigateToHome() {
    this.navigate("home") {
        launchSingleTop = true
        popUpTo(0)
    }
}
