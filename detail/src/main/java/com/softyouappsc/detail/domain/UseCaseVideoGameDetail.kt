package com.softyouappsc.detail.domain

import com.softyouappsc.data.repository.VideoGamesRepository
import com.softyouappsc.models.VideoGameDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCaseVideoGameDetail @Inject constructor(
    private val videoGamesRepository: VideoGamesRepository
) {
    operator fun invoke(idVG: Int): Flow<VideoGameDetail> {
        return videoGamesRepository.getVideoGameById(idVG)
    }

    fun getVideoGamesDB(id: Int): Flow<VideoGameDetail> {
        return videoGamesRepository.getVideoGameByIdDB(id)
    }
    fun saveVideoGame(videoGame: VideoGameDetail) {
        return videoGamesRepository.saveVideoGameInDB(videoGame)
    }

    fun deleteVideoGame(videoGame: VideoGameDetail) {
        return videoGamesRepository.deleteVideoGameDB(videoGame)
    }
}