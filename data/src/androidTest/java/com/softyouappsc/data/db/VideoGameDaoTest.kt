package com.softyouappsc.data.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.softyouappsc.data.datasource.database.room.VideoGamesDatabase
import org.junit.Before

abstract class VideoGameDaoTest {

    private lateinit var _db : VideoGamesDatabase
    val db: VideoGamesDatabase
        get() = _db

    @Before
    fun initProvider(){
        _db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            VideoGamesDatabase::class.java
        ).build()
    }

    fun closeDb(){
        _db.close()
    }
}