package com.kuneosu.newcompose.ui

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
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
import androidx.compose.runtime.rememberCoroutineScope
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
import com.kuneosu.newcompose.data.room.ToonDatabase
import com.kuneosu.newcompose.ui.screens.SearchScreen
import com.kuneosu.newcompose.ui.screens.SettingScreen
import com.kuneosu.newcompose.ui.screens.SplashScreen
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
import com.kuneosu.newcompose.viewModel.SearchViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

//private const val TAG = "LIFE_TRACKING"

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private val searchViewModel by viewModels<SearchViewModel>()
    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    @SuppressLint("CoroutineCreationDuringComposition")
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

                    composable("main_screen",
                        enterTransition = {
                            scaleIn(animationSpec = tween(700))
                        },
                        exitTransition = {
                            scaleOut(animationSpec = tween(700))
                        }
                    ) {
                        MainScreen(navController)
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
                        SettingScreen(navController = navController)
                    }
                    composable("search_screen") {
                        SearchScreen(navController = navController, viewModel = searchViewModel)
                    }
                }
            }
        }

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
    fun MainScreen(
        navController: NavController
    ) {


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
            MainContent()
        }
    }

    @Composable
    fun MainContent(
//        isLoading: Boolean
    ) {
        Column {
            MainTabRow()
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
    fun MainTabRow() {
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
                    MakeToonList(
                        bigToons = viewModel.bigToonList,
                        smallToons = viewModel.smallToonList
                    )
                }

                1 -> {
                    MakeToonList(
                        bigToons = viewModel.bigToonList,
                        smallToons = viewModel.smallToonList
                    )
                }

                2 -> {
                    MakeToonList(
                        bigToons = viewModel.bigToonList,
                        smallToons = viewModel.smallToonList
                    )
                }

                else -> {
                    MakeToonList(
                        bigToons = viewModel.bigToonList,
                        smallToons = viewModel.smallToonList
                    )
                }
            }
        }
    }
}

