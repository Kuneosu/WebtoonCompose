package com.kuneosu.newcompose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.kuneosu.newcompose.R
import com.kuneosu.newcompose.util.GifImage
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.navigate("main_screen")
    }

    SplashLogo()
}

@Composable
fun SplashLogo() {
    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        GifImage(source = R.drawable.splash, modifier = Modifier.fillMaxSize(), repeat = 0)
    }
}
