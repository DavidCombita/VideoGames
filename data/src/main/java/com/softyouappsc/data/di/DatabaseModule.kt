package com.softyouappsc.data.di

import android.content.Context
import androidx.room.Room
import com.softyouappsc.data.datasource.database.DataBaseDataSource
import com.softyouappsc.data.datasource.database.realtime.RealtimeDataSource
import com.softyouappsc.data.datasource.database.room.RoomDataSource
import com.softyouappsc.data.datasource.database.room.VideoGamesDao
import com.softyouappsc.data.datasource.database.room.VideoGamesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideVideoGamesDatabase(@ApplicationContext context: Context): VideoGamesDatabase {
        return Room.databaseBuilder(
            context,
            VideoGamesDatabase::class.java,
            "app_database_videogames"
        ).build()
    }

    @Provides
    fun provideVideoGamesDao(appDatabase: VideoGamesDatabase): VideoGamesDao =
        appDatabase.videoGameDao()

    @Provides
    @Singleton
    fun provideDataSourceDatabase(dao: VideoGamesDao): DataBaseDataSource =
        RoomDataSource(dao)

    /*@Provides
    @Singleton
    fun provideDataSourceDatabaseRealtime(): DataBaseDataSource = RealtimeDataSource()*/
}