package com.softyouappsc.data.network

import com.google.gson.Gson
import com.softyouappsc.data.CoroutinesTestRule
import com.softyouappsc.data.database.VideoGamesDao
import com.softyouappsc.data.models.VideoGameDetail
import com.softyouappsc.data.models.VideoGames
import com.softyouappsc.data.repository.VideoGamesRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import okio.buffer
import okio.source
import kotlinx.coroutines.flow.first
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NetworkRepositoryTest {

    private lateinit var mockNetworkService: VideoGamesApiHelper

    private lateinit var videoGameRepository: VideoGamesRepository

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Before
    fun setUp(){
        mockNetworkService = object : VideoGamesApiHelper {
            override suspend fun getListVideoGames(): VideoGames {
                return enqueueResponseVideoGames("api-response.json")
            }

            override suspend fun getVideoGameById(id: Int): VideoGameDetail {
                return enqueueResponseVideoGameDetail("api-response-by-id.json")
            }
        }
        videoGameRepository = VideoGamesRepository(mockNetworkService, MockVideoGameDao())
    }

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

    @Test
    fun `obtener lista de juegos emitiendo VideoGameDetail DB`() = runBlocking {
        val expectedVideoGameDetail = enqueueResponseVideoGameDetail("api-response-by-id.json")

        flow {
            emit(listOf( expectedVideoGameDetail))
        }

        val result = videoGameRepository.getListVideoGamesDB()

        val actualVideoGames = result.first()

        assertEquals(expectedVideoGameDetail.id, actualVideoGames[0].id)
        assertEquals(452, actualVideoGames[0].id)
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

class MockVideoGameDao : VideoGamesDao {
    override fun getAllVideoGames(): Flow<List<VideoGameDetail>> {
        return flow {
            emit(listOf(enqueueResponseVideoGameDetail("api-response-by-id.json")))
        }
    }

    override fun saveVideoGameDetail(data: VideoGameDetail) {}

    override fun getVideoGameById(idVG: Int): Flow<VideoGameDetail> {
        return flow {
            emit(enqueueResponseVideoGameDetail("api-response-by-id.json"))
        }
    }

    private fun enqueueResponseVideoGameDetail(file: String): VideoGameDetail {
        val inputStrem = javaClass.classLoader?.getResourceAsStream("api_response/$file")
            ?.source()?.buffer()!!.readString(Charsets.UTF_8)
        return Gson().fromJson(inputStrem, VideoGameDetail::class.java)
    }

}

