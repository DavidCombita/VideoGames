package com.softyouappsc.data.network

import com.softyouappsc.models.VideoGameDetail
import com.softyouappsc.models.VideoGames
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoGamesApi {
    @GET("/api/games")
    suspend fun getListVideoGames(): Response<VideoGames>

    @GET("/api/game")
    suspend fun getVideoGameById(@Query("id") idVG: Int): Response<VideoGameDetail>

}