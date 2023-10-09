package com.softyouappsc.data.datasource.database.room

import com.softyouappsc.data.datasource.database.DataBaseDataSource
import com.softyouappsc.models.VideoGameDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomDataSource @Inject constructor(
    private val videoGameDao: VideoGamesDao
): DataBaseDataSource {
    override fun getAllVideoGames(): Flow<List<VideoGameDetail>> = videoGameDao.getAllVideoGames()

    override fun saveVideoGameDetail(data: VideoGameDetail) {
        videoGameDao.saveVideoGameDetail(data)
    }

    override fun getVideoGameById(idVG: Int): Flow<VideoGameDetail> {
        return videoGameDao.getVideoGameById(idVG)
    }

    override fun deleteVideoGameDB(deleteGame: VideoGameDetail) {
        videoGameDao.deleteVideoGameDB(deleteGame)
    }
}