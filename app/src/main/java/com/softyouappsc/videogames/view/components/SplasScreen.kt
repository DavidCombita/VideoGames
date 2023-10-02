package com.softyouappsc.videogames.view.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.softyouappsc.videogames.R
import com.softyouappsc.videogames.view.navigation.navigateToHome
import kotlinx.coroutines.delay

const val splashNavigationRoute = "splash"
@Composable
fun SplashScreen(navController: NavHostController = rememberNavController()){

    LaunchedEffect(key1 = true ){
        delay(2000)
        navController.navigateToHome()
    }

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_splash))
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
    }
}