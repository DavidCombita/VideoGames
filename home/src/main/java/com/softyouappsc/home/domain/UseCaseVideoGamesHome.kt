package com.softyouappsc.home.domain

import com.softyouappsc.data.repository.VideoGamesRepository
import com.softyouappsc.models.VideoGames
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCaseVideoGamesHome @Inject constructor(
    private val videoGamesRepository: VideoGamesRepository
) {
    operator fun invoke(): Flow<VideoGames> {
        return videoGamesRepository.getListVideoGames()
    }

    fun getListDataBase(): Flow<VideoGames> {
        return videoGamesRepository.getListVideoGamesDB()
    }
}