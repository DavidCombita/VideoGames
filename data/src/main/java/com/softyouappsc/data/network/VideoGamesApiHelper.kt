package com.softyouappsc.data.network

import com.softyouappsc.data.models.VideoGameDetail
import com.softyouappsc.data.models.VideoGames
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface VideoGamesApiHelper {

    suspend fun getListVideoGames(): VideoGames?

    suspend fun getVideoGameById(idVG: Int): VideoGameDetail?
}