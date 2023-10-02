package com.softyouappsc.detail.domain

import com.softyouappsc.data.repository.VideoGamesRepository
import com.softyouappsc.detail.view.viewmodels.DetailViewModel
import com.softyouappsc.models.VideoGameDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class UseCaseVideoGameDetailTest {

    private lateinit var useCase: UseCaseVideoGameDetail
    private lateinit var videoGamesRepository: VideoGamesRepository
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        videoGamesRepository = mock(VideoGamesRepository::class.java)
        useCase = UseCaseVideoGameDetail(videoGamesRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `invoke should return flow of VideoGameDetail`() = runBlockingTest {
        // Given
        val videoGameId = 1
        val expectedVideoGameDetail = mock(VideoGameDetail::class.java)
        `when`(videoGamesRepository.getVideoGameById(videoGameId)).thenReturn(flowOf(expectedVideoGameDetail))

        // When
        val resultFlow = useCase.invoke(videoGameId).toList()

        // Then
        assertEquals(1, resultFlow.size)
        assertEquals(expectedVideoGameDetail, resultFlow[0])
    }

    @Test
    fun `getVideoGamesDB should return flow of VideoGameDetail`() = runBlockingTest {
        // Given
        val videoGameId = 1
        val expectedVideoGameDetail = mock(VideoGameDetail::class.java)
        `when`(videoGamesRepository.getVideoGameByIdDB(videoGameId)).thenReturn(flowOf(expectedVideoGameDetail))

        // When
        val resultFlow = useCase.getVideoGamesDB(videoGameId).toList()

        // Then
        assertEquals(1, resultFlow.size)
        assertEquals(expectedVideoGameDetail, resultFlow[0])
    }

}