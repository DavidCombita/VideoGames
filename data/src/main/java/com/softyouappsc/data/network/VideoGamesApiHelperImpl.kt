package com.softyouappsc.data.network

import com.softyouappsc.data.models.VideoGameDetail
import com.softyouappsc.data.models.VideoGames
import retrofit2.Response
import javax.inject.Inject

class VideoGamesApiHelperImpl @Inject constructor(
    private val apiVideoGame: VideoGamesApi
): VideoGamesApiHelper {

    override suspend fun getListVideoGames(): VideoGames? =
        apiVideoGame.getListVideoGames().body()


    override suspend fun getVideoGameById(idVG: Int): VideoGameDetail? =
        apiVideoGame.getVideoGameById(idVG).body()
}