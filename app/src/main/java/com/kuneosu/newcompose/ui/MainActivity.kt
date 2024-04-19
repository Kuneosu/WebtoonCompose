package com.kuneosu.newcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kuneosu.newcompose.BackPressedCallBack
import com.kuneosu.newcompose.data.model.DataProvider
import com.kuneosu.newcompose.ui.theme.NewComposeTheme
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                        MainScreen()
                    }
                }

            }
        }
    }
}

@Composable
internal fun MainScreen(
) {
    val activity = LocalContext.current as MainActivity
    val backPressedCallback = BackPressedCallBack(activity)
    activity.onBackPressedDispatcher.addCallback(activity, backPressedCallback.callback)

    val state = rememberCollapsingToolbarScaffoldState()


    CollapsingToolbarScaffold(
        modifier = Modifier
            .fillMaxSize(),
        state = state,
        scrollStrategy = ScrollStrategy.EnterAlways,
        toolbar = {
            MainTopBar()
        },
    ) {
        MainContent()
    }

}

//@OptIn(ExperimentalPagerApi::class)
//@Composable
//fun MainTabRow() {
//    val pages = listOf("지금 핫한", "오늘 연재무료", "실시간 랭킹", "오늘 뭐볼까?")
//    val pagerState = rememberPagerState(initialPage = pages.size / 2)
//    val coroutineScope = rememberCoroutineScope()
//    val deviceWidth = LocalConfiguration.current.screenWidthDp.dp
//
//    ScrollableTabRow(
//        selectedTabIndex = pagerState.currentPage,
//        backgroundColor = Color.Black,
//        edgePadding = deviceWidth / 3,
//        indicator = {
//            TabRowDefaults.Indicator(
//                modifier = Modifier
//                    .pagerTabIndicatorOffset(pagerState, it)
//                    .height(0.dp),
//                color = Color.White,
//            )
//        },
//    ) {
//        pages.forEachIndexed { index, text ->
//            val selected = pagerState.currentPage == index
//            Tab(
//                modifier = Modifier
//                    .background(Color.Black)
//                    .padding(horizontal = 0.dp),
//                selected = selected,
//                onClick = {
//                    coroutineScope.launch {
//                        pagerState.animateScrollToPage(index)
//                    }
//                },
//                text = {
//                    if (index == pagerState.currentPage) {
//                        when (index) {
//                            0 -> {
//                                GradientAnimationButton(
//                                    text = text,
//                                    startColor = Color(156, 39, 176, 255),
//                                    endColor = Color(0, 188, 212, 255)
//                                )
//                            }
//
//                            1 -> {
//                                GradientAnimationButton(
//                                    text = text,
//                                    startColor = Color(255, 193, 7, 255),
//                                    endColor = Color(14, 228, 255, 255)
//                                )
//                            }
//
//                            2 -> {
//                                GradientAnimationButton(
//                                    text = text,
//                                    startColor = Color(255, 235, 59, 255),
//                                    endColor = Color(233, 30, 99, 255)
//                                )
//                            }
//
//                            else -> {
//                                GradientAnimationButton(
//                                    text = text,
//                                    startColor = Color(137, 255, 0, 255),
//                                    endColor = Color(0, 188, 212, 255)
//                                )
//                            }
//                        }
//
//                    } else {
//                        GradientAnimationButton(
//                            text = text,
//                            startColor = Color(60, 60, 60, 255),
//                            endColor = Color(60, 60, 60, 255)
//                        )
//                    }
//
//
//                }
//            )
//        }
//    }
//
//    HorizontalPager(
//        count = pages.size,
//        state = pagerState,
//        verticalAlignment = Alignment.CenterVertically,
//    ) { page ->
//        when (page) {
//            0 -> {
//                MakeSevenToons(toons = DataProvider.toonList2)
//
//            }
//
//            1 -> {
//                MakeSevenToons(toons = DataProvider.toonList)
//
//            }
//
//            2 -> {
//                MakeSevenToons(toons = DataProvider.toonList2)
//
//            }
//
//            else -> {
//                MakeSevenToons(toons = DataProvider.toonList)
//
//            }
//        }
//    }
//}

@Composable
fun MainContent() {
    Column {
        MainTabRow()
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    NewComposeTheme {

        MainScreen()

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainTabRow() {
    val pages = listOf("지금 핫한", "오늘 연재무료", "실시간 랭킹", "오늘 뭐볼까?")
    val pagerState = androidx.compose.foundation.pager.rememberPagerState(
        initialPage = pages.size / 2
        , pageCount = { pages.size }
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
                                    startColor = Color(156, 39, 176, 255),
                                    endColor = Color(0, 188, 212, 255)
                                )
                            }

                            1 -> {
                                GradientAnimationButton(
                                    text = text,
                                    startColor = Color(255, 193, 7, 255),
                                    endColor = Color(14, 228, 255, 255)
                                )
                            }

                            2 -> {
                                GradientAnimationButton(
                                    text = text,
                                    startColor = Color(255, 235, 59, 255),
                                    endColor = Color(233, 30, 99, 255)
                                )
                            }

                            else -> {
                                GradientAnimationButton(
                                    text = text,
                                    startColor = Color(137, 255, 0, 255),
                                    endColor = Color(0, 188, 212, 255)
                                )
                            }
                        }

                    } else {
                        GradientAnimationButton(
                            text = text,
                            startColor = Color(60, 60, 60, 255),
                            endColor = Color(60, 60, 60, 255)
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
                MakeSevenToons(toons = DataProvider.toonList2)

            }

            1 -> {
                MakeSevenToons(toons = DataProvider.toonList)

            }

            2 -> {
                MakeSevenToons(toons = DataProvider.toonList2)

            }

            else -> {
                MakeSevenToons(toons = DataProvider.toonList)

            }
        }
    }
}