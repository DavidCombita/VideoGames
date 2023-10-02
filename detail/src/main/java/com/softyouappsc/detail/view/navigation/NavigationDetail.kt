package com.softyouappsc.detail.view.navigation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.softyouappsc.detail.view.ui.components.DetailComponents
import java.net.URLDecoder
import java.net.URLEncoder

const val LINKED_ID_GAME = "idDetailGame"
const val detailNavigationRoute = "detail_game/{$LINKED_ID_GAME}"

fun NavGraphBuilder.detailVideoGame(onDetailClick: (Int) -> Unit,
                                    onBackClick: () -> Unit) {
    composable(
        route = detailNavigationRoute,
        arguments = listOf(navArgument(LINKED_ID_GAME) { type = NavType.IntType })
    ) {
        val videoGameId = it.arguments?.getInt(LINKED_ID_GAME) ?: 0
        DetailComponents(onDetailClick, onBackClick, videoGameId)
    }

}