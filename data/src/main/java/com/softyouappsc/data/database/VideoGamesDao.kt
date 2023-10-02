package com.softyouappsc.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.softyouappsc.models.VideoGameDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoGamesDao {
    @Query("SELECT * FROM VideoGames")
    fun getAllVideoGames(): Flow<List<VideoGameDetail>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveVideoGameDetail(data: VideoGameDetail)

    @Query("SELECT * FROM VideoGames WHERE id = :idVG")
    fun getVideoGameById(idVG: Int): Flow<VideoGameDetail>

    @Delete
    fun deleteVideoGameDB(deleteGame: VideoGameDetail)
}