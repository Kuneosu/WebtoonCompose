package com.kuneosu.newcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kuneosu.newcompose.R
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
    val deviceWidth = LocalConfiguration.current.screenWidthDp / 2

    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.kakao_webtoon_logo),
            contentDescription = "logo",
            modifier = Modifier.size(deviceWidth.dp)
        )
//        GifImage(source = R.drawable.splash, modifier = Modifier.fillMaxSize(), repeat = 0)
    }
}
