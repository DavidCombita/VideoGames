package com.softyouappsc.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.softyouappsc.data.models.VideoGameDetail
import com.softyouappsc.data.utils.Converters

@Database(entities = [VideoGameDetail::class], version = 1)
@TypeConverters(Converters::class)
abstract class VideoGamesDatabase : RoomDatabase() {
    abstract fun videoGameDao(): VideoGamesDao
}