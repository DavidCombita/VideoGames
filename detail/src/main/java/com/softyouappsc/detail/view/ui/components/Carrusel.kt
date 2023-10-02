package com.softyouappsc.detail.view.ui.components
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import com.google.accompanist.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        infiniteLoop = false,
        initialPage = 1
    )

    Text(text = "Galeria",
        modifier = Modifier.padding(bottom = 10.dp).fillMaxSize(),
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
        color = Color.Black)

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(start = 5.dp, end = 5.dp)
    ) { page ->
        Card(
            colors = CardDefaults.cardColors(Color.Transparent),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(0.dp),
            modifier = Modifier
        ) {
            val scaleFactor = calculateScaleFactorForPage(page, pagerState)
            AsyncImage(
                model = list[page].image,
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .width(300.dp * scaleFactor)
                    .padding(end = 5.dp, start = if (page > 0) 5.dp else 0.dp)
                    .offset {
                        val pageOffset = this@HorizontalPager.calculateCurrentOffsetForPage(page)
                        IntOffset(
                            x = (70.dp * pageOffset).roundToPx(),
                            y = 0,
                        )
                    },
                contentScale = ContentScale.Fit
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
private fun calculateScaleFactorForPage(page: Int, pagerState: com.google.accompanist.pager.PagerState): Float {
    val minScale = 0.9f
    val maxScale = 0.8f
    val scaleFactor = 0.1f

    val pageOffset = pagerState.currentPageOffset.absoluteValue
    return when {
        page < pagerState.currentPage -> minScale + scaleFactor * pageOffset
        page > pagerState.currentPage -> minScale + scaleFactor * (1 - pageOffset)
        else -> maxScale
    }
}