package com.softyouappsc.detail.view.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.softyouappsc.detail.view.viewmodels.DetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailComponents(
    onDetailClick: (Int, Boolean) -> Unit,
    onBackClick: () -> Unit ,
    idVideoGame: Int,
    isDB: Boolean,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    var loading by remember { mutableStateOf(false) }
    val game by viewModel.detail.collectAsState()
    val save by viewModel.save.collectAsState()

    LaunchedEffect(idVideoGame) {
        loading = true
        viewModel.getDataDetail(idVideoGame, isDB)
    }

    val mContext = LocalContext.current
    if(save){
        mToast(mContext,isDB)
    }

    Scaffold(
        floatingActionButton = { FloatingActionButton(
            onClick = {
                if(isDB){
                    viewModel.deleteVideoGame(game!!)
                    onBackClick()
                }else{
                    viewModel.saveDBVideoGame(game!!)
                }
            },
            ) {
                Icon(Icons.Filled.Favorite, "Floating action button.")
            }
        },
    ) { innerPadding ->
        if (game != null) {
            Column(modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())) {

                Text(text = game!!.title,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxSize(),
                    textAlign = TextAlign.Center,
                    fontSize = 23.sp,
                    color = Color.Black
                )

                AsyncImage(
                    model = game!!.thumbnail,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(
                            bottom = 5.dp, start = 5.dp,
                            end = 5.dp
                        )
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Fit
                )

                Text(text = "Descripción",
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                        .fillMaxSize(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    color = Color.Black)
                Text(text = game!!.description ?: "", modifier = Modifier.padding(bottom = 5.dp))
                Text(text = "Desarrollador: ${game!!.developer}", modifier = Modifier.padding(bottom = 10.dp))

                BuildSlider(game!!.screenshots?: emptyList())

                Text(text = "Url: ${game!!.game_url}", modifier = Modifier.padding(bottom = 10.dp))
                Text(text = "Requisitos Minimos",
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                        .fillMaxSize(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    color = Color.Black)
                Text(text = "Sistema opertivo: ${game!!.minimum_system_requirements!!.os}",
                    modifier = Modifier.padding(bottom = 10.dp))
                Text(text = "Memoria ram: ${game!!.minimum_system_requirements!!.memory}",
                    modifier = Modifier.padding(bottom = 10.dp))
                Text(text = "Grafica: ${game!!.minimum_system_requirements!!.graphics}",
                    modifier = Modifier.padding(bottom = 10.dp))
                Text(text = "Espacio disco: ${game!!.minimum_system_requirements!!.storage}",
                    modifier = Modifier.padding(bottom = 10.dp))
                Text(text = "Procesador: ${game!!.minimum_system_requirements!!.processor}",
                    modifier = Modifier.padding(bottom = 10.dp))
            }
        } else {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        }
    }

}

private fun mToast(context: Context, isDB: Boolean){
    if(!isDB){
        Toast.makeText(context, "Se guardo el juego con exito", Toast.LENGTH_LONG).show()
    }else{
        Toast.makeText(context, "Se elimino el juego con exito", Toast.LENGTH_LONG).show()
    }

}


