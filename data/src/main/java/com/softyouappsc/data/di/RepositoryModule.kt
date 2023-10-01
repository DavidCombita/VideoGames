package com.softyouappsc.data.di

import com.softyouappsc.data.database.VideoGamesDao
import com.softyouappsc.data.network.VideoGamesApiHelper
import com.softyouappsc.data.repository.VideoGamesRepository
import com.softyouappsc.data.repository.VideoGamesRepositoryHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideNetworkRepository(videoGamesApiHelper: VideoGamesApiHelper,
                                 videoGamesDao: VideoGamesDao
    ): VideoGamesRepositoryHelper =
        VideoGamesRepository(videoGamesApiHelper, videoGamesDao)
}