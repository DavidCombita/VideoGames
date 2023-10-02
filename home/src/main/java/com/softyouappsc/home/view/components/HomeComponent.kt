package com.softyouappsc.home.view.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import com.google.accompanist.pager.HorizontalPager
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.softyouappsc.home.view.viewmodel.HomeViewModel
import com.softyouappsc.models.VideoGameDetail
import com.softyouappsc.models.VideoGames
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat


@Composable
fun ProgressCenter() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class,
    ExperimentalPagerApi::class
)
@Composable
fun HomeComponent(
    onHomeClick: () -> Unit,
    onDetailClick: (Int) -> Unit,
    navController: NavHostController = rememberNavController(),
    viewModel: HomeViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()

    var selectedItem by remember {
        mutableStateOf(0)
    }
    val items = listOf(
        Triple("Home", Icons.Filled.Home, "principal"),
        Triple("Favs", Icons.Filled.Favorite, "save")
    )

    val pagerState = rememberPagerState(
        pageCount = items.size,
        initialOffscreenLimit = 2,
        infiniteLoop = false,
        initialPage = 0
    )

    LaunchedEffect(pagerState.currentPage) {
        selectedItem = pagerState.currentPage
    }

    val games by viewModel.games.collectAsState()

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(item.second, contentDescription = item.first) },
                        label = { Text(item.first) },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
            ) { page ->
                if (selectedItem == 0) {
                    if (games != null) {
                        LazyListGames(games, onDetailClick)
                    } else {
                        ProgressCenter()
                    }
                } else {
                    Text(
                        text = "guardados",
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun LazyListGames(
    games: VideoGames?,
    onDetailClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(games!!) { item ->
            CardHomeListGames(item, onDetailClick)
        }
    }
}

@Composable
fun CardHomeListGames(videoGame: VideoGameDetail,
                      onDetailClick: (Int) -> Unit){
    Card(modifier = Modifier
        .padding(
            start = 10.dp,
            bottom = 8.dp,
            end = 10.dp,
            top = 10.dp
        )
        .clickable {
            onDetailClick(videoGame.id)
        },
        colors = CardDefaults.cardColors(Color.Transparent),
        shape = RoundedCornerShape(20.dp),
        border = CardDefaults.outlinedCardBorder(true)
    ) {
        Text(
            text = videoGame.title,
            modifier = Modifier
                .padding(bottom = 5.dp, start = 5.dp, top = 5.dp)
                .fillMaxSize(),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = Color.Black
        )
        AsyncImage(
            model = videoGame.thumbnail,
            contentDescription = null,
            modifier = Modifier
                .padding(
                    bottom = 20.dp, start = 5.dp,
                    end = 5.dp, top = 10.dp
                )
                .fillMaxWidth()
                .height(150.dp),
            contentScale = ContentScale.Fit
        )
        Card(modifier = Modifier.padding(start = 10.dp, end = 10.dp,
            bottom = 10.dp),
            shape = RoundedCornerShape(20.dp)) {
            Text(
                text = "Genero: "+videoGame.genre,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(
                        start = 5.dp, bottom = 3.dp
                    )
            )
            Text(
                text = "Descripción corta: "+videoGame.short_description,
                color = Color.Black,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(
                        start = 5.dp, bottom = 5.dp
                    )
            )
            Text(
                text = "Plataforma: "+ videoGame.platform,
                color = Color.Black,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(
                        start = 5.dp, bottom = 5.dp
                    )
            )
            Text(
                text = "Editor: "+ videoGame.publisher,
                color = Color.Black,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(
                        start = 5.dp, bottom = 5.dp
                    )
            )
            Text(
                text = "Fecha de lanzamiento: "+ cambiarFormatoFecha(videoGame.release_date),
                color = Color.Black,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(
                        start = 5.dp, bottom = 5.dp
                    )
            )
        }

    }
}

private fun cambiarFormatoFecha(fecha: String): String {
    return try {
        val sdfOriginal = SimpleDateFormat("yyyy-MM-dd")
        val fecha = sdfOriginal.parse(fecha)
        val sdfDestino = SimpleDateFormat("dd/MM/yyyy")
        sdfDestino.format(fecha)
    } catch (e: ParseException) {
        e.printStackTrace()
        fecha
    }
}

