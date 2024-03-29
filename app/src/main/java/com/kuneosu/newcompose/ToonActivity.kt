package com.kuneosu.newcompose

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColor
import androidx.core.graphics.toColorInt
import androidx.core.graphics.toColorLong
import com.kuneosu.newcompose.ui.theme.NewComposeTheme
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

class ToonActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NewComposeTheme {
                val toonBackground = intent.getIntExtra("toon_background", 0)
                if (intent.hasExtra("toon_main_gif")) {
                    val toonMainGif = intent.getIntExtra("toon_main_gif", 0)
                    ToonScreen(
                        toonBackground = toonBackground,
                        toonMainGif = toonMainGif,
                    )
                } else if (intent.hasExtra("toon_main_image")) {
                    val toonMainImage = intent.getIntExtra("toon_main_image", 0)
                    ToonScreen(
                        toonBackground = toonBackground,
                        toonMainImage = toonMainImage,
                    )
                } else {
                    finish()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ToonScreen(
    toonBackground: Int,
    toonMainGif: Int? = null,
    toonMainImage: Int? = null,
) {

    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = toonBackground),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        val state = rememberCollapsingToolbarScaffoldState()

        CollapsingToolbarScaffold(
            modifier = Modifier
                .fillMaxSize(),
            state = state,
            scrollStrategy = ScrollStrategy.EnterAlways,
            toolbar = {
                ToonScreenTopBar(
                    toonMainGif = toonMainGif,
                    toonMainImage = toonMainImage,
                )
            },
        ) {
            ToonScreenTabRow()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToonScreenTopBar(
    toonMainGif: Int? = null,
    toonMainImage: Int? = null,
) {
    val deviceWidth = LocalConfiguration.current.screenWidthDp.dp
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            if (toonMainGif != null) {
                GifImage(
                    source = toonMainGif,
                    modifier = Modifier
                        .size(deviceWidth)
                        .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = painterResource(id = toonMainImage!!),
                    contentDescription = "",
                    modifier = Modifier
                        .size(deviceWidth)
                        .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
        Divider(color = Color.White, thickness = 0.5.dp)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "뒤끝작렬",
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ToonScreenTabRow() {
    val scrollState = rememberScrollState()
    val pages = listOf("첫 화 보기", "회차", "정보", "이용권", "댓글")
    val pagerState = androidx.compose.foundation.pager.rememberPagerState() {
        pages.size
    }


    val coroutineScope = rememberCoroutineScope()
    val deviceWidth = LocalConfiguration.current.screenWidthDp.dp
    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color.Black,
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
                            Text(
                                text = text,
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        } else {
                            Text(
                                text = text,
                                color = Color.LightGray,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
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
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(900.dp)
                            .background(Color.Red)
                            .verticalScroll(scrollState)
                    )
                }
                1 -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(900.dp)
                            .background(Color.Green)
                            .verticalScroll(scrollState)
                    )
                }
                2 -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(900.dp)
                            .background(Color.Blue)
                            .verticalScroll(scrollState)
                    )
                }
                3 -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(900.dp)
                            .background(Color.Yellow)
                            .verticalScroll(scrollState)
                    )
                }
                4 -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(900.dp)
                            .background(Color.Cyan)
                            .verticalScroll(scrollState)
                    )
                }
            }
        }
    }


}

