package com.softyouappsc.data.repository

import com.softyouappsc.data.datasource.database.DataBaseDataSource
import com.softyouappsc.data.datasource.database.room.VideoGamesDao
import com.softyouappsc.models.VideoGameDetail
import com.softyouappsc.models.VideoGames
import com.softyouappsc.data.datasource.network.VideoGamesApiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class VideoGamesRepository @Inject constructor(
    private val apiHelper: VideoGamesApiHelper,
    private val dataBaseDataSource: DataBaseDataSource
): VideoGamesRepositoryHelper  {


    override fun getListVideoGames(): Flow<VideoGames> = flow {
        apiHelper.getListVideoGames()?.let { emit(it) }
    }.flowOn(Dispatchers.IO)

    override fun getVideoGameById(idVG: Int): Flow<VideoGameDetail> = flow {
        apiHelper.getVideoGameById(idVG)?.let { emit(it) }
    }.flowOn(Dispatchers.IO)

    override fun getListVideoGamesDB(): Flow<VideoGames> {
        return dataBaseDataSource.getAllVideoGames().map { list ->
            val videoGames = VideoGames()
            videoGames.addAll(list)
            videoGames
        }
    }

    override fun getVideoGameByIdDB(idVG: Int): Flow<VideoGameDetail> {
        return dataBaseDataSource.getVideoGameById(idVG)
    }

    override fun saveVideoGameInDB(videoGame: VideoGameDetail) {
        dataBaseDataSource.saveVideoGameDetail(videoGame)
    }

    override fun deleteVideoGameDB(videoGame: VideoGameDetail) {
        dataBaseDataSource.deleteVideoGameDB(videoGame)
    }

}