package com.softyouappsc.data.network

import retrofit2.http.GET

interface VideGamesApiHelper {

    suspend fun getListVideoGames():Unit

    suspend fun getVideoGameById(idVG: Int): Unit
}