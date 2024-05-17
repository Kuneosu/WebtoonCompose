package com.kuneosu.newcompose.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kuneosu.newcompose.data.room.ToonDatabase
import com.kuneosu.newcompose.ui.screens.MainScreen
import com.kuneosu.newcompose.ui.screens.SearchScreen
import com.kuneosu.newcompose.ui.screens.SettingScreen
import com.kuneosu.newcompose.ui.screens.SplashScreen
import com.kuneosu.newcompose.ui.theme.NewComposeTheme
import com.kuneosu.newcompose.viewModel.MainViewModel
import com.kuneosu.newcompose.viewModel.SearchViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private val searchViewModel by viewModels<SearchViewModel>()
    private val coroutineScope = CoroutineScope(Dispatchers.Default)


    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val themeMode by viewModel.themeMode.collectAsState()
            Crossfade(targetState = themeMode, label = "") { selectedTheme ->
                NewComposeTheme(selectedTheme) {
                    val navController = rememberNavController()
                    MainNavHost(navController)
                }
            }
            createDatabase()
        }
    }

    private fun createDatabase() {
        val db = ToonDatabase.getDatabase(applicationContext)
        coroutineScope.launch {
            db.toonDao().deleteAllBigToon()
            db.toonDao().deleteAllSmallToon()
            viewModel.bigToonList.forEach {
                db.toonDao().insertBigToon(it)
            }
            viewModel.smallToonList.forEach {
                db.toonDao().insertSmallToon(it)
            }
        }
    }

    @Composable
    private fun MainNavHost(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = "splash_screen"
        ) {

            composable("splash_screen") {
                SplashScreen(navController)
            }

            composable("main_screen",
                enterTransition = {
                    scaleIn(animationSpec = tween(700))
                },
                exitTransition = {
                    scaleOut(animationSpec = tween(700))
                }
            ) {
                MainScreen(navController, viewModel)
            }
            composable("setting_screen",
                enterTransition = {
                    slideIntoContainer(
                        animationSpec = tween(500),
                        towards = AnimatedContentTransitionScope.SlideDirection.Left
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        animationSpec = tween(500),
                        towards = AnimatedContentTransitionScope.SlideDirection.Right
                    )
                }) {
                SettingScreen(navController = navController, viewModel = viewModel)
            }
            composable("search_screen") {
                SearchScreen(navController = navController, viewModel = searchViewModel)
            }
        }
    }
}




