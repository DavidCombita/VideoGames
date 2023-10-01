package com.softyouappsc.data.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.softyouappsc.data.database.VideoGamesDao
import com.softyouappsc.data.database.VideoGamesDatabase
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

}