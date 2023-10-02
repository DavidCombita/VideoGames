package com.softyouappsc.detail.view.ui.components

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.softyouappsc.detail.view.viewmodels.DetailViewModel

@Composable
fun DetailComponents(
    onDetailClick: (Int) -> Unit,
    onBackClick: () -> Unit ,
    idVideoGame: Int,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    Log.e("numero enviado",idVideoGame.toString())
    Text(text = "Hola soy david iniciaaa")
    viewModel.getDataDetail(452)
}

