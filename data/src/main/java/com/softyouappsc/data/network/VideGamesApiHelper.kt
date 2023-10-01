package com.softyouappsc.data.network

import com.softyouappsc.data.models.VideoGameDetail
import com.softyouappsc.data.models.VideoGames
import kotlinx.coroutines.flow.Flow

interface VideGamesApiHelper {

    suspend fun getListVideoGames(): Flow<VideoGames>

    suspend fun getVideoGameById(idVG: Int): Flow<VideoGameDetail>
}