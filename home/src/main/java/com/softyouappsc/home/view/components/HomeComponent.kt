package com.softyouappsc.home.view.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.google.accompanist.pager.HorizontalPager
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.softyouappsc.home.view.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalPagerApi::class
)
@Composable
fun HomeComponent(
    onHomeClick: () -> Unit,
    onDetailClick: (Int) -> Unit,
    navController: NavHostController = rememberNavController(),
    viewModel: HomeViewModel = hiltViewModel()
){
    val coroutineScope = rememberCoroutineScope()

    var selectedItem by remember {
        mutableStateOf(0)
    }
    val items = listOf(Triple("Home", Icons.Filled.Home, "principal"),
        Triple("Favs", Icons.Filled.Favorite, "save"))

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
            NavigationBar{
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(item.second, contentDescription = item.first) },
                        label = { Text(item.first) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    )
                }
            }
        }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            HorizontalPager(state = pagerState,
                modifier = Modifier.fillMaxSize(),
                ) { page ->
                if(selectedItem == 0){
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    ){
                        games?.let { gameList ->
                            items(gameList) { item ->
                                Text(
                                    text = item.title,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.Black,
                                    modifier = Modifier.padding(start = 10.dp,
                                        bottom = 30.dp)
                                        .clickable {
                                            onDetailClick(item.id)
                                        }
                                )
                            }
                        }
                    }
                }else{
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
