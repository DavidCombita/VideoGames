package com.softyouappsc.data.network

import retrofit2.http.GET

interface VideoGamesApi {
    @GET("/api/games")
    suspend fun getListVideoGames():Unit

    @GET("/api/game?id={id}")
    suspend fun getVideoGameById(idVG: Int): Unit

}