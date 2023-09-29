package com.softyouappsc.data.network

import javax.inject.Inject

class VideoGamesApiHelperImpl @Inject constructor(
    private val apiService: VideoGamesApi
): VideGamesApiHelper {

    override suspend fun getListVideoGames() {
        TODO("Not yet implemented")
    }

    override suspend fun getVideoGameById(idVG: Int) {
        TODO("Not yet implemented")
    }

}