package com.softyouappsc.home.view.viewmodel

import androidx.lifecycle.ViewModel
import com.softyouappsc.home.domain.UseCaseVideoGamesHome
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCaseVideoGameDetail: UseCaseVideoGamesHome
): ViewModel() {



    init {

    }
}