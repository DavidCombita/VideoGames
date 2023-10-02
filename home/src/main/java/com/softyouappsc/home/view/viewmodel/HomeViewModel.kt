package com.softyouappsc.home.view.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softyouappsc.home.domain.UseCaseVideoGamesHome
import com.softyouappsc.models.VideoGames
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCaseVideoGameDetail: UseCaseVideoGamesHome
): ViewModel() {

    private val _games = MutableStateFlow<VideoGames?>(null)
    val games: StateFlow<VideoGames?> get() = _games

    private val _gamesDB = MutableStateFlow<VideoGames?>(null)
    val gamesDB: StateFlow<VideoGames?> get() = _gamesDB

    fun getVideoGames() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                useCaseVideoGameDetail().collect { result ->
                    _games.value = result
                }
            }
        }
    }

    fun getVideoGamesDB() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                useCaseVideoGameDetail.getListDataBase().collect{
                    _gamesDB.value = it
                }
            }
        }
    }
}