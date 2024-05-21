package com.kuneosu.newcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kuneosu.newcompose.R
import com.kuneosu.newcompose.ui.theme.ThemeMode
import com.kuneosu.newcompose.viewModel.MainViewModel
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController, viewModel: MainViewModel) {

    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.navigate("main_screen")
    }

    SplashLogo(viewModel = viewModel)
}

@Composable
fun SplashLogo(viewModel: MainViewModel) {
    val themeMode by viewModel.themeMode.collectAsState()
    val logo = when (themeMode) {
        ThemeMode.DARK -> R.drawable.kakao_clone_by_kks_dark
        ThemeMode.LIGHT -> R.drawable.kakao_clone_by_kks_light
        ThemeMode.SYSTEM -> if (isSystemInDarkTheme()) R.drawable.kakao_clone_by_kks_dark else R.drawable.kakao_clone_by_kks_light
    }

    val logoWidth = LocalConfiguration.current.screenWidthDp / 2

    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(id = logo),
            contentDescription = "logo",
            modifier = Modifier.size(logoWidth.dp)
        )
//        GifImage(source = R.drawable.splash, modifier = Modifier.fillMaxSize(), repeat = 0)
    }
}
