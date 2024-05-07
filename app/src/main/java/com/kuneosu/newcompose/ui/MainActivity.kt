package com.kuneosu.newcompose.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ScrollableTabRow
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Tab
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kuneosu.newcompose.R
import com.kuneosu.newcompose.ui.theme.EndGradientButton1
import com.kuneosu.newcompose.ui.theme.EndGradientButton2
import com.kuneosu.newcompose.ui.theme.EndGradientButton3
import com.kuneosu.newcompose.ui.theme.EndGradientButton4
import com.kuneosu.newcompose.ui.theme.NewComposeTheme
import com.kuneosu.newcompose.ui.theme.StartGradientButton1
import com.kuneosu.newcompose.ui.theme.StartGradientButton2
import com.kuneosu.newcompose.ui.theme.StartGradientButton3
import com.kuneosu.newcompose.ui.theme.StartGradientButton4
import com.kuneosu.newcompose.ui.theme.UnselectedButton
import com.kuneosu.newcompose.util.BackPressedCallBack
import com.kuneosu.newcompose.util.GradientAnimationButton
import com.kuneosu.newcompose.viewModel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

private const val TAG = "LIFE_TRACKING"

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "MainActivity : OnCreate")
        setContent {
            NewComposeTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "splash_screen"
                ) {

                    composable("splash_screen") {
                        SplashScreen(navController)
                    }

                    composable("main_screen") {
                        var isLoading by remember {
                            mutableStateOf(true)
                        }
                        Log.d(TAG, "Main : isLoading = $isLoading")
                        LaunchedEffect(key1 = true) {
                            delay(3000L)
                            Log.d(TAG, "3sec delayed on Main")
                            isLoading = false
                            Log.d(TAG, "delayed after isLoading = $isLoading")

                        }
                        MainScreen(isLoading, navController)
                    }
                    composable("setting_screen") {
                        SettingScreen(navController = navController)
                    }
                }
            }
        }
    }

    @Composable
    fun MainScreen(
        isLoading: Boolean,
        navController: NavController
    ) {
        Log.d(TAG, "MainScreen Load")
        val mainActivity = LocalContext.current as MainActivity
        val backPressedCallback = BackPressedCallBack(mainActivity)
        mainActivity.onBackPressedDispatcher.addCallback(mainActivity, backPressedCallback.callback)

        val state = rememberCollapsingToolbarScaffoldState()

        CollapsingToolbarScaffold(
            modifier = Modifier
                .fillMaxSize(),
            state = state,
            scrollStrategy = ScrollStrategy.EnterAlways,
            toolbar = {
                MainTopBar(navController = navController)
            },
        ) {
            Log.d(TAG, "Collapsing Toolbar Content Load")
            MainContent(isLoading)
        }
    }

    @Composable
    fun MainContent(
        isLoading: Boolean
    ) {
        Column {
            Log.d(TAG, "MainContent Load")
            MainTabRow(isLoading)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Preview() {
        NewComposeTheme {
//            MainScreen()
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun MainTabRow(isLoading: Boolean) {
        Log.d(TAG, "MainTabRow Load")
        val pages = listOf(
            stringResource(R.string.tab_list_title_1),
            stringResource(R.string.tab_list_title_2),
            stringResource(R.string.tab_list_title_3),
            stringResource(R.string.tab_list_title_4)
        )

        val pagerState = androidx.compose.foundation.pager.rememberPagerState(
            initialPage = pages.size / 2, pageCount = { pages.size }
        )

        val coroutineScope = rememberCoroutineScope()
        val deviceWidth = LocalConfiguration.current.screenWidthDp.dp

        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color.Black,
            edgePadding = deviceWidth / 3,
        ) {
            pages.forEachIndexed { index, text ->
                val selected = pagerState.currentPage == index
                Tab(
                    modifier = Modifier
                        .background(Color.Black)
                        .padding(horizontal = 0.dp, vertical = 5.dp),
                    selected = selected,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        if (index == pagerState.currentPage) {
                            when (index) {
                                0 -> {
                                    GradientAnimationButton(
                                        text = text,
                                        startColor = StartGradientButton1,
                                        endColor = EndGradientButton1
                                    )
                                }

                                1 -> {
                                    GradientAnimationButton(
                                        text = text,
                                        startColor = StartGradientButton2,
                                        endColor = EndGradientButton2
                                    )
                                }

                                2 -> {
                                    GradientAnimationButton(
                                        text = text,
                                        startColor = StartGradientButton3,
                                        endColor = EndGradientButton3
                                    )
                                }

                                else -> {
                                    GradientAnimationButton(
                                        text = text,
                                        startColor = StartGradientButton4,
                                        endColor = EndGradientButton4
                                    )
                                }
                            }

                        } else {
                            GradientAnimationButton(
                                text = text,
                                startColor = UnselectedButton,
                                endColor = UnselectedButton
                            )
                        }
                    }
                )
            }
        }

        androidx.compose.foundation.pager.HorizontalPager(
            beyondBoundsPageCount = 3,
            state = pagerState,
            verticalAlignment = Alignment.CenterVertically,
        ) { page ->
            when (page) {
                0 -> {
                    if (isLoading) {
                        Log.d(TAG, "Page 0, isLoading = $isLoading ")
                        ShimmerToons()
                    } else {
                        Log.d(TAG, "Page 0, isLoading = $isLoading ")
                        MakeSevenToons(toons = viewModel.toonList2)
                    }
                }

                1 -> {
                    if (isLoading) {
                        Log.d(TAG, "Page 1, isLoading = $isLoading ")
                        ShimmerToons()
                    } else {
                        Log.d(TAG, "Page 1, isLoading = $isLoading ")
                        MakeSevenToons(toons = viewModel.toonList1)
                    }
//                    MakeSevenToons(toons = viewModel.toonList1)
                }

                2 -> {
                    Log.d(TAG, "Page 2, isLoading = $isLoading ")
                    MakeSevenToons(toons = viewModel.toonList2)
                }

                else -> {
                    if (isLoading) {
                        Log.d(TAG, "Page 3, isLoading = $isLoading ")
                        ShimmerToons()
                    } else {
                        Log.d(TAG, "Page 3, isLoading = $isLoading ")
                        MakeSevenToons(toons = viewModel.toonList1)
                    }
//                    MakeSevenToons(toons = viewModel.toonList1)

                }
            }
        }
    }
}

