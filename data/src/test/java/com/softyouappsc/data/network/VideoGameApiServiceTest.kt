package com.softyouappsc.data.network

import com.softyouappsc.data.datasource.network.VideoGamesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.both
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers.greaterThan
import org.hamcrest.Matchers.lessThan
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class VideoGameApiServiceTest {

    private lateinit var mockServer: MockWebServer

    lateinit var service: VideoGamesApi

    @Before
    fun createService() {
        mockServer = MockWebServer()
        service = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mockServer.url("/"))
            .build()
            .create(VideoGamesApi::class.java)
    }

    @After
    fun closeServer() {
        mockServer.shutdown()
    }

    @Test
    fun `obtener lista de video juegos`() = runBlocking (Dispatchers.IO){
        enqueueResponseVideoGames("api-response.json")

        val data = service.getListVideoGames().body()
        val request = mockServer.takeRequest()

        MatcherAssert.assertThat(request.path, CoreMatchers.`is`("/api/games"))
        MatcherAssert.assertThat(request.method, CoreMatchers.`is`("GET"))

        MatcherAssert.assertThat(data?.size, CoreMatchers.`is`(387))
    }

    @Test
    fun `obtener video juegos por id`() = runBlocking (Dispatchers.IO){
        enqueueResponseVideoGames("api-response-by-id.json")

        val data = service.getVideoGameById(452).body()
        val request = mockServer.takeRequest()

        MatcherAssert.assertThat(request.path, CoreMatchers.`is`("/api/game?id=452"))
        MatcherAssert.assertThat(request.method, CoreMatchers.`is`("GET"))

        MatcherAssert.assertThat(data?.id, CoreMatchers.`is`(452))
        MatcherAssert.assertThat(data?.game_url, CoreMatchers.`is`("https://www.freetogame.com/open/call-of-duty-warzone"))
        MatcherAssert.assertThat(data?.developer, CoreMatchers.`is`("Infinity Ward"))
    }

    @Test
    fun `manejar error de red`() = runBlocking(Dispatchers.IO) {
        enqueueErrorResponse(404)

        val response = service.getListVideoGames()
        MatcherAssert.assertThat(response.code(), CoreMatchers.`is`(404))
        MatcherAssert.assertThat(response.body(), CoreMatchers.nullValue())
    }

    @Test
    fun `manejar error de red 200`() = runBlocking(Dispatchers.IO) {
        enqueueErrorResponse(200)

        val response = service.getListVideoGames()
        MatcherAssert.assertThat(response.code(), CoreMatchers.`is`(200))
        MatcherAssert.assertThat(response.body(), CoreMatchers.notNullValue())
    }

    @Test
    fun `manejar tiempo de espera`() = runBlocking(Dispatchers.IO) {
        enqueueDelayedResponse(2, "api-response.json")

        val startTime = System.currentTimeMillis()
        service.getListVideoGames()
        val endTime = System.currentTimeMillis()

        // Verificar si la duración de la solicitud está dentro de un rango aceptable
        MatcherAssert.assertThat(endTime - startTime,
            CoreMatchers.`is`( both(greaterThan(2000L)).and(lessThan(3000L))))
    }

    private fun enqueueDelayedResponse(delay: Long, file: String) {
        val inputStrem = javaClass.classLoader?.getResourceAsStream("api_response/$file")
            ?.source()?.buffer()
        mockServer.enqueue(
            MockResponse().setBody(inputStrem!!.readString(Charsets.UTF_8))
                .setBodyDelay(delay, TimeUnit.SECONDS)
        )
    }

    private fun enqueueResponseVideoGames(file: String){
        val inputStrem = javaClass.classLoader?.getResourceAsStream("api_response/$file")
            ?.source()?.buffer()
        mockServer.enqueue(
            MockResponse().setBody(inputStrem!!.readString(Charsets.UTF_8))
        )
    }

    private fun enqueueErrorResponse(responseCode: Int) {
        val mockResponse = MockResponse().setResponseCode(responseCode)
        if(responseCode == 200){
            val inputStrem = javaClass.classLoader?.getResourceAsStream("api_response/api-response.json")
                ?.source()?.buffer()
            mockResponse.setBody(inputStrem!!.readString(Charsets.UTF_8))
        }
        mockServer.enqueue(mockResponse)
    }
}