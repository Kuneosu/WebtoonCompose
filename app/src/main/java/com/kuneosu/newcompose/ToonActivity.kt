package com.kuneosu.newcompose

import android.content.ContentResolver
import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Divider
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.palette.graphics.Palette
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.korean.KoreanTextRecognizerOptions
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
                val toonTitle = intent.getIntExtra("toon_title", 0)
                if (intent.hasExtra("toon_main_gif")) {
                    val toonMainGif = intent.getIntExtra("toon_main_gif", 0)
                    ToonScreen(
                        toonBackground = toonBackground,
                        toonMainGif = toonMainGif,
                        toonTitle = toonTitle
                    )
                } else if (intent.hasExtra("toon_main_image")) {
                    val toonMainImage = intent.getIntExtra("toon_main_image", 0)
                    ToonScreen(
                        toonBackground = toonBackground,
                        toonMainImage = toonMainImage,
                        toonTitle = toonTitle
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
    toonTitle: Int
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background
        Image(
            painter = painterResource(id = toonBackground),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        val state = rememberCollapsingToolbarScaffoldState()
        val mainColor = mainColor(LocalContext.current, toonBackground)

        CollapsingToolbarScaffold(
            modifier = Modifier
                .fillMaxSize(),
            state = state,
            scrollStrategy = ScrollStrategy.EnterAlways,
            toolbar = {
                ToonScreenTopBar(
                    mainColor = mainColor,
                    toonMainGif = toonMainGif,
                    toonMainImage = toonMainImage,
                    toonTitle = toonTitle
                )
            },
        ) {
            ToonScreenTabRow(mainColor = mainColor)
        }
    }
}


@Composable
fun ToonScreenTopBar(
    mainColor: Int,
    toonMainGif: Int? = null,
    toonMainImage: Int? = null,
    toonTitle: Int
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
            val gradient = Brush.verticalGradient(
                colors = listOf(Color(mainColor), Color(0, 0, 0, 0)),
                startY = 100f, 0f,
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(gradient),
                contentAlignment = Alignment.Center
            ) {
                GetTitle(context = LocalContext.current, toonTitle = toonTitle)
            }

        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ToonScreenTabRow(mainColor: Int) {
    val scrollState = rememberScrollState()
    val pages = listOf("첫 화 보기", "회차", "정보", "이용권", "댓글")
    val pagerState = androidx.compose.foundation.pager.rememberPagerState {
        pages.size
    }


    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .background(Color(mainColor))
            .fillMaxSize()
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color(mainColor),
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                        .padding(vertical = 5.dp),
                    color = Color.White
                )
            },
        ) {
            pages.forEachIndexed { index, text ->
                val selected = pagerState.currentPage == index
                Tab(
                    selected = selected,
                    selectedContentColor = Color.Transparent,
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
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        } else {
                            Text(
                                text = text,
                                color = Color(255, 255, 255, 150),
                                fontSize = 10.sp,
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
                    ) {

                    }
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

@Composable
fun GetTitle(context: Context, toonTitle: Int) {

    val imageUri = Uri.Builder()
        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
        .authority(context.resources.getResourcePackageName(toonTitle))
        .appendPath(context.resources.getResourceTypeName(toonTitle))
        .appendPath(context.resources.getResourceEntryName(toonTitle))
        .build()

    val image = InputImage.fromFilePath(context, imageUri)
    var recognizedText by remember {
        mutableStateOf("")
    }
    val recognizer = TextRecognition.getClient(KoreanTextRecognizerOptions.Builder().build())
    recognizer.process(image)
        .addOnSuccessListener {
            val result = it.text
            Log.d("ToonScreenTabRow", "Text recognition success: $result")
            recognizedText = result

        }
        .addOnFailureListener {
            Log.e("ToonScreenTabRow", "Text recognition failed")
        }
    Log.d("ToonScreenTabRow", "GetTitle: $recognizedText")
    AddTitle(title = recognizedText.replace("\n", " "))
}

@Composable
fun AddTitle(title: String) {
    Text(
        text = title,
        color = Color.White,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(20.dp)
    )
}

fun mainColor(context: Context, background: Int): Int {
    val toonBitmap = BitmapFactory.decodeResource(context.resources, background)
    val mainColor = Palette.from(toonBitmap).generate().dominantSwatch?.rgb
    return mainColor!!
}