package com.softyouappsc.data.network

import com.softyouappsc.models.VideoGameDetail
import com.softyouappsc.models.VideoGames

interface VideoGamesApiHelper {

    suspend fun getListVideoGames(): VideoGames?

    suspend fun getVideoGameById(idVG: Int): VideoGameDetail?
}