package com.softyouappsc.data.network

import com.softyouappsc.data.models.VideoGameDetail
import com.softyouappsc.data.models.VideoGames
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class VideoGamesApiHelperImpl @Inject constructor(
    private val apiService: VideoGamesApi
): VideGamesApiHelper {

    override suspend fun getListVideoGames(): Flow<VideoGames> = flow<VideoGames> {
        TODO("Not yet implemented")
    }.catch { e ->
        println(e.message)
    }.flowOn(Dispatchers.IO)


    override suspend fun getVideoGameById(idVG: Int): Flow<VideoGameDetail> = flow<VideoGameDetail> {
        TODO("Not yet implemented")
    }.catch { e ->
        println(e.message)
    }.flowOn(Dispatchers.IO)

}