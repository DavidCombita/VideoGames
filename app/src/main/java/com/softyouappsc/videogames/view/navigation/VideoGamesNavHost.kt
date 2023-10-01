package com.softyouappsc.videogames.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.softyouappsc.detail.view.navigation.detailNavigationRoute
import com.softyouappsc.detail.view.navigation.detailVideoGame

@Composable
fun VideoGamesNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = detailNavigationRoute,
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        detailVideoGame ( onDetailClick = navController::navigateToDetail )
    }
}

fun NavController.navigateToDetail(videoGameId: Int) {
    this.navigate("detail_game/$videoGameId") {
        launchSingleTop = true
    }
}