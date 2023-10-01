package com.softyouappsc.data.repository

import com.softyouappsc.data.models.VideoGameDetail
import com.softyouappsc.data.models.VideoGames
import com.softyouappsc.data.network.VideoGamesApiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class VideoGamesRepository @Inject constructor(
    private val apiHelper: VideoGamesApiHelper
): VideoGamesRepositoryHelper  {

    override fun getListVideoGames(): Flow<VideoGames> = flow {
        apiHelper.getListVideoGames()?.let { emit(it) }
    }.catch { e ->
        println(e.message)
    }.flowOn(Dispatchers.IO)


    override fun getVideoGameById(idVG: Int): Flow<VideoGameDetail> = flow {
        apiHelper.getVideoGameById(idVG)?.let { emit(it) }
    }.catch { e ->
        println(e.message)
    }.flowOn(Dispatchers.IO)
}