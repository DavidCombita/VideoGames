package com.softyouappsc.detail.view.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softyouappsc.detail.domain.UseCaseVideoGameDetail
import com.softyouappsc.models.VideoGameDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCaseVideoGameDetail: UseCaseVideoGameDetail
): ViewModel() {

    var state by mutableStateOf<VideoGameDetail?>(null)
        private set

    fun getDataDetail(id: Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                useCaseVideoGameDetail(id).collect{
                    Log.e("Se obtvo viewmodel", it.title)
                }
            }
        }
    }
}