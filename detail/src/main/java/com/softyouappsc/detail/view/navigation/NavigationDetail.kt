package com.softyouappsc.detail.view.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.softyouappsc.detail.view.ui.components.DetailComponents
import kotlin.reflect.KFunction2

const val LINKED_ID_GAME = "idDetailGame"
const val LINKED_IS_DB = "isDB"
const val detailNavigationRoute = "detail_game/{$LINKED_ID_GAME}/{$LINKED_IS_DB}"

fun NavGraphBuilder.detailVideoGame(
    onDetailClick: (Int, Boolean) -> Unit,
    onBackClick: () -> Unit) {
    composable(
        route = detailNavigationRoute,
        arguments = listOf(
            navArgument(LINKED_ID_GAME) { type = NavType.IntType },
            navArgument(LINKED_IS_DB) { type = NavType.BoolType })
    ) {
        val videoGameId = it.arguments?.getInt(LINKED_ID_GAME) ?: 0
        val isDB = it.arguments?.getBoolean(LINKED_IS_DB) ?: false
        DetailComponents(onDetailClick, onBackClick, videoGameId, isDB)
    }

}