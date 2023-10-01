package com.softyouappsc.data.repository

import com.softyouappsc.data.database.VideoGamesDao
import com.softyouappsc.data.models.VideoGameDetail
import com.softyouappsc.data.models.VideoGames
import com.softyouappsc.data.network.VideoGamesApiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class VideoGamesRepository @Inject constructor(
    private val apiHelper: VideoGamesApiHelper,
    private val videoGameDao: VideoGamesDao
): VideoGamesRepositoryHelper  {


    override fun getListVideoGames(): Flow<VideoGames> = flow {
        apiHelper.getListVideoGames()?.let { emit(it) }
    }.flowOn(Dispatchers.IO)

    override fun getVideoGameById(idVG: Int): Flow<VideoGameDetail> = flow {
        apiHelper.getVideoGameById(idVG)?.let { emit(it) }
    }.flowOn(Dispatchers.IO)

    override fun getListVideoGamesDB(): Flow<VideoGames> {
        return videoGameDao.getAllVideoGames().map { list ->
            val videoGames = VideoGames()
            videoGames.addAll(list)
            videoGames
        }
    }

    override fun getVideoGameByIdDB(idVG: Int): Flow<VideoGameDetail> {
        return videoGameDao.getVideoGameById(idVG)
    }

    override fun saveVideoGameInDB(videoGame: VideoGameDetail) {
        videoGameDao.saveVideoGameDetail(videoGame)
    }
}