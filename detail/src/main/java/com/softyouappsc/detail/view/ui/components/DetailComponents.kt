package com.softyouappsc.detail.view.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.softyouappsc.detail.view.viewmodels.DetailViewModel
import com.softyouappsc.models.VideoGameDetail
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DetailComponents(
    onDetailClick: (Int) -> Unit,
    onBackClick: () -> Unit ,
    idVideoGame: Int,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    var loading by remember { mutableStateOf(false) }
    val games by viewModel.detail.collectAsState()

    LaunchedEffect(idVideoGame) {
        loading = true
        viewModel.getDataDetail(idVideoGame)
    }

    if (games != null) {
        Column(modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())) {

            Text(text = games!!.title,
                modifier = Modifier.padding(top = 10.dp).fillMaxSize(),
                textAlign = TextAlign.Center,
                fontSize = 23.sp,
                color = Color.Black
            )

            AsyncImage(
                model = games!!.thumbnail,
                contentDescription = null,
                modifier = Modifier
                    .padding(bottom = 5.dp, start = 5.dp,
                            end = 5.dp)
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Fit
            )

            Text(text = "Descripción",
                modifier = Modifier.padding(bottom = 5.dp).fillMaxSize(),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Color.Black)
            Text(text = games!!.description ?: "", modifier = Modifier.padding(bottom = 5.dp))
            Text(text = "Desarrollador: ${games!!.developer}", modifier = Modifier.padding(bottom = 10.dp))

            BuildSlider(games!!.screenshots?: emptyList())
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


