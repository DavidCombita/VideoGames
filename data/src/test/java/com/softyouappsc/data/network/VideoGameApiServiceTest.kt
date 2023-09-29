package com.softyouappsc.data.network

import com.softyouappsc.data.models.VideoGames
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.regex.Matcher
import javax.inject.Inject

@RunWith(JUnit4::class)
@HiltAndroidTest
class VideoGameApiServiceTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var mockServer: MockWebServer

    @Inject
    lateinit var service: VideoGamesApi

    @Before
    fun createService() {
        hiltRule.inject()
        mockServer = MockWebServer()
    }

    @After
    fun closeServer() {
        mockServer.shutdown()
    }

    @Test
    fun `obtener lista de video juegos`() = runBlocking (Dispatchers.IO){
        enqueueResponseVideoGames("api-response.json")

        val data = service.getListVideoGames()
        val request = mockServer.takeRequest()
        val result = (data as VideoGames)

        MatcherAssert.assertThat(request.method, CoreMatchers.`is`("GET"))
    }

    fun enqueueResponseVideoGames(file: String){
        val inputStrem = javaClass.classLoader?.getResourceAsStream("api_response/$file")
            ?.source()?.buffer()
        mockServer.enqueue(
            MockResponse().setBody(inputStrem!!.readString(Charsets.UTF_8))
        )
    }
}