package com.softyouappsc.detail.view.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softyouappsc.detail.domain.UseCaseVideoGameDetail
import com.softyouappsc.models.VideoGameDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCaseVideoGameDetail: UseCaseVideoGameDetail
): ViewModel() {

    private val _gamesDetail = MutableStateFlow<VideoGameDetail?>(null)
    val detail: StateFlow<VideoGameDetail?> get() = _gamesDetail

    private val _save = MutableStateFlow<Boolean>(false)
    val save: StateFlow<Boolean> get() = _save

    private val _delete = MutableStateFlow<Boolean>(false)
    val delete: StateFlow<Boolean> get() = _delete

    fun getDataDetail(id: Int, isDB: Boolean){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                if(isDB){
                    useCaseVideoGameDetail.getVideoGamesDB(id).collect{game ->
                        Log.e("DB info", "Db ñjsdnaslkjasj")
                        _gamesDetail.value = game
                    }
                }else{
                    getInfoApi(id)
                }
            }
        }
    }

    fun deleteVideoGame(videoGame: VideoGameDetail) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try {
                    useCaseVideoGameDetail.deleteVideoGame(videoGame)
                    Log.e("eliminado", "--------------------------------")
                    _delete.value = true
                }catch (e: Exception){
                    _delete.value = false
                }

            }
        }
    }

    private suspend fun getInfoApi(id: Int) {
        useCaseVideoGameDetail(id).collect { gameApi ->
            viewModelScope.launch {
                Log.e("api", "api----------------")
                _gamesDetail.value = gameApi
            }
        }
    }

    fun saveDBVideoGame(game: VideoGameDetail) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try {
                    useCaseVideoGameDetail.saveVideoGame(game)
                    Log.e("guardado", "--------------------------------")
                    _save.value = true
                }catch (e: Exception){
                    _save.value = false
                }

            }
        }
    }
}