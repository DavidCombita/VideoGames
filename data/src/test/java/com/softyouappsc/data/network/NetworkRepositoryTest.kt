package com.softyouappsc.data.network

import com.google.gson.Gson
import com.softyouappsc.data.models.VideoGameDetail
import com.softyouappsc.data.models.VideoGames
import com.softyouappsc.data.repository.VideoGamesRepository
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import okio.buffer
import okio.source
import kotlinx.coroutines.flow.first

import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class NetworkRepositoryTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()


    private val mockNetworkService = object : VideoGamesApiHelper {
        override suspend fun getListVideoGames(): VideoGames {
            return enqueueResponseVideoGames("api-response.json")
        }

        override suspend fun getVideoGameById(id: Int): VideoGameDetail {
            return enqueueResponseVideoGameDetail("api-response-by-id.json")
        }
    }

    // Sujeto de prueba
    private val videoGameRepository = VideoGamesRepository(mockNetworkService)

    @Test
    fun `obtener lista de juegos emitiendo VideoGames`() = runBlocking {
        val expectedVideoGames = enqueueResponseVideoGames("api-response.json")

        flow {
            emit(expectedVideoGames)
        }

        val result = videoGameRepository.getListVideoGames()

        val actualVideoGames = result.first()

        assertEquals(expectedVideoGames, actualVideoGames)
    }

    @Test
    fun `obtener lista de juegos emitiendo VideoGameDetail`() = runBlocking {
        val expectedVideoGameDetail = enqueueResponseVideoGameDetail("api-response-by-id.json")

        flow {
            emit(expectedVideoGameDetail)
        }

        val result = videoGameRepository.getVideoGameById(452)

        val actualVideoGames = result.first()

        assertEquals(expectedVideoGameDetail, actualVideoGames)
        assertEquals(452, actualVideoGames.id)
    }

    private fun enqueueResponseVideoGames(file: String): VideoGames {
        val inputStrem = javaClass.classLoader?.getResourceAsStream("api_response/$file")
            ?.source()?.buffer()!!.readString(Charsets.UTF_8)
        return Gson().fromJson(inputStrem, VideoGames::class.java)
    }

    private fun enqueueResponseVideoGameDetail(file: String): VideoGameDetail {
        val inputStrem = javaClass.classLoader?.getResourceAsStream("api_response/$file")
            ?.source()?.buffer()!!.readString(Charsets.UTF_8)
        return Gson().fromJson(inputStrem, VideoGameDetail::class.java)
    }
}