package com.softyouappsc.detail.view.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.softyouappsc.detail.view.ui.components.DetailComponents

const val LINKED_ID_GAME = "idDetailGame"
const val detailNavigationRoute = "detail_game/{$LINKED_ID_GAME}"

fun NavController.navigateToDetailGame(navOptions: NavOptions? = null) {
    this.navigate(detailNavigationRoute, navOptions)
}

fun NavGraphBuilder.detailVideoGame(onDetailClick: (Int) -> Unit) {
    composable(
        route = detailNavigationRoute,
        arguments = listOf(
            navArgument(LINKED_ID_GAME) { type = NavType.IntType },
        ),
    ) {
        DetailComponents(onDetailClick)
    }
}