package com.kuneosu.newcompose.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kuneosu.newcompose.R
import com.kuneosu.newcompose.ui.MainActivity
import com.kuneosu.newcompose.ui.theme.EndGradientButton1
import com.kuneosu.newcompose.ui.theme.EndGradientButton2
import com.kuneosu.newcompose.ui.theme.EndGradientButton3
import com.kuneosu.newcompose.ui.theme.EndGradientButton4
import com.kuneosu.newcompose.ui.theme.StartGradientButton1
import com.kuneosu.newcompose.ui.theme.StartGradientButton2
import com.kuneosu.newcompose.ui.theme.StartGradientButton3
import com.kuneosu.newcompose.ui.theme.StartGradientButton4
import com.kuneosu.newcompose.util.BackPressedCallBack
import com.kuneosu.newcompose.util.GradientAnimationButton
import com.kuneosu.newcompose.util.MainTopBar
import com.kuneosu.newcompose.util.MakeToonList
import com.kuneosu.newcompose.viewModel.MainViewModel
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
fun MainScreen(
    navController: NavController, viewModel: MainViewModel
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
            MainTopBar(navController = navController, viewModel = viewModel)
        },
    ) {
        MainTabRow(viewModel = viewModel)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainTabRow(viewModel: MainViewModel) {
    val pages = listOf(
        stringResource(R.string.tab_list_title_1),
        stringResource(R.string.tab_list_title_2),
        stringResource(R.string.tab_list_title_3),
        stringResource(R.string.tab_list_title_4)
    )

    val pageCount = pages.size

    val pagerState = androidx.compose.foundation.pager.rememberPagerState(
        initialPage = pageCount / 2, pageCount = { pageCount }
    )

    val coroutineScope = rememberCoroutineScope()
    val deviceWidth = LocalConfiguration.current.screenWidthDp.dp

    Column {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = MaterialTheme.colorScheme.background,
            edgePadding = deviceWidth / 3,
            indicator = {
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.background(Color.Transparent),
                    height = 2.dp,
                    color = Color.Transparent
                )
            }
        ) {
            pages.forEachIndexed { index, text ->
                val selected = pagerState.currentPage == index
                Tab(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
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
                            Surface(
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(
                                        1.dp,
                                        MaterialTheme.colorScheme.onTertiaryContainer,
                                        RoundedCornerShape(16.dp)
                                    )
                            ) {
                                Text(
                                    text = text,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.tertiary,
                                    modifier = Modifier
                                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                                        .padding(horizontal = 24.dp, vertical = 12.dp)
                                )
                            }
                        }
                    }
                )
            }

        }

        HorizontalPager(
            beyondBoundsPageCount = 3,
            state = pagerState,
            verticalAlignment = Alignment.CenterVertically,
        ) { page ->
            when (page) {
                0 -> {
                    MakeToonList(
                        bigToons = viewModel.bigToonList,
                        smallToons = viewModel.smallToonList,
                        viewModel = viewModel
                    )
                }

                1 -> {
                    MakeToonList(
                        bigToons = viewModel.bigToonList,
                        smallToons = viewModel.smallToonList,
                        viewModel = viewModel
                    )
                }

                2 -> {
                    MakeToonList(
                        bigToons = viewModel.bigToonList,
                        smallToons = viewModel.smallToonList,
                        viewModel = viewModel
                    )
                }

                else -> {
                    MakeToonList(
                        bigToons = viewModel.bigToonList,
                        smallToons = viewModel.smallToonList,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}