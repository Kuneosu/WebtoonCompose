package com.kuneosu.newcompose.ui

import android.util.Log
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

private const val TAG = "LIFE_TRACKING"

@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(key1 = true) {
        Log.d(TAG, "Activate SplashScreen.")
        delay(2000)
        Log.d(TAG, "2sec delayed on Splash, Navigate Main")
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
        Log.d(TAG, "SplashLogo Image Load")
        Image(
            painter = painterResource(id = R.drawable.kakao_webtoon_logo),
            contentDescription = "logo",
            modifier = Modifier.size(deviceWidth.dp)
        )
//        GifImage(source = R.drawable.splash, modifier = Modifier.fillMaxSize(), repeat = 0)
    }
}
