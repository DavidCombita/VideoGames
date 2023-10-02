package com.softyouappsc.detail.view.ui.components
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import com.google.accompanist.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.softyouappsc.models.Screenshot
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BuildSlider(list: List<Screenshot>) {
    val pagerState = rememberPagerState(
        pageCount = list.size,
        initialOffscreenLimit = 5,
        infiniteLoop = true,
        initialPage = 1
    )

    Text(text = "Galeria")
    
    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(start = 5.dp, end = 5.dp)
    ) { page ->
        AsyncImage(
            model = list[page].image,
            contentDescription = null,
            modifier = Modifier
                .height(220.dp)
                .width(300.dp)
                .padding(end = 5.dp),
            contentScale = ContentScale.Fit
        )
    }
}