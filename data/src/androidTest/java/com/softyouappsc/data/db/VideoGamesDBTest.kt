package com.softyouappsc.data.db

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.gson.Gson
import com.softyouappsc.data.models.VideoGameDetail
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VideoGamesDBTest: VideoGameDaoTest() {

    @Test
    fun when_insert_data_in_video_games () = runBlocking {
        val insertDetail = enqueueResponseVideoGameDetail("api-response-by-id.json")
        db.videoGameDao().saveVideoGameDetail(insertDetail)

        val load =  db.videoGameDao().getAllVideoGames().take(1).toList()[0]

        assertThat(load.size, CoreMatchers.`is`(1))
        assertThat(load[0].id, CoreMatchers.`is`(452))
        assertThat(load[0].title, CoreMatchers.`is`("Call Of Duty: Warzone"))
    }

    @Test
    fun when_insert_data_in_video_games_duplicate () = runBlocking {
        val insertDetail = enqueueResponseVideoGameDetail("api-response-by-id.json")
        db.videoGameDao().saveVideoGameDetail(insertDetail)

        val load =  db.videoGameDao().getAllVideoGames().take(1).toList()[0]
        assertThat(load.size, CoreMatchers.`is`(1))
        assertThat(load[0].id, CoreMatchers.`is`(452))
        assertThat(load[0].title, CoreMatchers.`is`("Call Of Duty: Warzone"))

        val insertDetail2 = enqueueResponseVideoGameDetail("api-response-by-id-2.json")
        db.videoGameDao().saveVideoGameDetail(insertDetail2)
        val load2 =  db.videoGameDao().getAllVideoGames().take(1).toList()[0]
        assertThat(load2.size, CoreMatchers.`is`(1))
        assertThat(load2[0].id, CoreMatchers.`is`(452))
        assertThat(load2[0].title, CoreMatchers.`is`("Diablo Immortal"))
    }

    @Test
    fun when_search_data_in_video_gamese () = runBlocking {
        val insertDetail = enqueueResponseVideoGameDetail("api-response-by-id.json")
        db.videoGameDao().saveVideoGameDetail(insertDetail)

        val load =  db.videoGameDao().getVideoGameById(452).take(1).toList()[0]
        assertThat(load.id, CoreMatchers.`is`(452))
        assertThat(load.title,  CoreMatchers.`is`("Call Of Duty: Warzone"))
        assertThat(load.developer,CoreMatchers.`is`("Infinity Ward"))
    }

    @Test
    fun when_search_data_in_video_gamese_no_inserted () = runBlocking {
        val load =  db.videoGameDao().getVideoGameById(452).take(1).toList()[0]
        assertThat(load, CoreMatchers.nullValue())
    }

    private fun enqueueResponseVideoGameDetail(file: String): VideoGameDetail {
        val inputStrem = javaClass.classLoader?.getResourceAsStream("api_response/$file")
            ?.source()?.buffer()!!.readString(Charsets.UTF_8)
        return Gson().fromJson(inputStrem, VideoGameDetail::class.java)
    }
}