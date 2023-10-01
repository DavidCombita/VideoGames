package com.softyouappsc.data.repository

import com.softyouappsc.data.models.VideoGameDetail
import com.softyouappsc.data.models.VideoGames
import kotlinx.coroutines.flow.Flow

interface VideoGamesRepositoryHelper {

    fun getListVideoGames(): Flow<VideoGames>

    fun getVideoGameById(idVG: Int): Flow<VideoGameDetail>
}
