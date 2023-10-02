package com.softyouappsc.data.network

import com.softyouappsc.models.VideoGameDetail
import com.softyouappsc.models.VideoGames
import javax.inject.Inject

class VideoGamesApiHelperImpl @Inject constructor(
    private val apiVideoGame: VideoGamesApi
): VideoGamesApiHelper {

    override suspend fun getListVideoGames(): VideoGames? =
        apiVideoGame.getListVideoGames().body()


    override suspend fun getVideoGameById(idVG: Int): VideoGameDetail? =
        apiVideoGame.getVideoGameById(idVG).body()
}