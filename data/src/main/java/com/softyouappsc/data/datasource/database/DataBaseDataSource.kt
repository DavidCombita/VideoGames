package com.softyouappsc.data.datasource.database

import com.softyouappsc.models.VideoGameDetail
import kotlinx.coroutines.flow.Flow

interface DataBaseDataSource {

    fun getAllVideoGames(): Flow<List<VideoGameDetail>>

    fun saveVideoGameDetail(data: VideoGameDetail)

    fun getVideoGameById(idVG: Int): Flow<VideoGameDetail>

    fun deleteVideoGameDB(deleteGame: VideoGameDetail)
}