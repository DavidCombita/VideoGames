package com.softyouappsc.data.datasource.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.softyouappsc.models.VideoGameDetail
import com.softyouappsc.models.utils.Converters

@Database(entities = [VideoGameDetail::class], version = 1)
@TypeConverters(Converters::class)
abstract class VideoGamesDatabase : RoomDatabase() {
    abstract fun videoGameDao(): VideoGamesDao
}