package com.softyouappsc.detail.view.ui.components

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.softyouappsc.detail.view.viewmodels.DetailViewModel

@Composable
fun DetailComponents(
    onTopicClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    Text(text = "Hola soy david iniciaaa")
    Log.e("Comienza", "-----------------------------------------------------")
    viewModel.getDataDetail(452)
}

