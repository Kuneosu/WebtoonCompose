package com.kuneosu.newcompose.util

import android.content.Intent
import android.os.Handler
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.kuneosu.newcompose.R
import com.kuneosu.newcompose.data.room.BigToon
import com.kuneosu.newcompose.data.room.SmallToon
import com.kuneosu.newcompose.ui.activity.ToonActivity
import com.kuneosu.newcompose.viewModel.MainViewModel

//private const val TAG = "LIFE_TRACKING"
enum class SIZE {
    SMALL,
    BIG
}


@Composable
fun MakeToonList(bigToons: List<BigToon>, smallToons: List<SmallToon>, viewModel: MainViewModel) {

    val configuration = LocalConfiguration.current
    val screenSize by remember {
        mutableIntStateOf(configuration.screenWidthDp)
    }

    if (screenSize > 720) {
        val chunkedSmallToons = smallToons.chunked(8)
        var toonCount = 0
        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth()
        ) {
            item {
                bigToons.forEach { bigToon ->
                    // Big Toon 개수가 대비 Small Toon이 부족할 경우,
                    // Small Toon 대비 Big Toon이 부족할 경우 해결 필요
                    if (toonCount >= chunkedSmallToons.size) {
                        BigToonCard(toon = bigToon, viewModel = viewModel, sizeMode = SIZE.BIG)
                    } else {
                        DisplayCard(
                            bigToon,
                            chunkedSmallToons[toonCount],
                            viewModel,
                            sizeMode = SIZE.BIG
                        )
                    }
                    toonCount++
                    if (bigToons.size < chunkedSmallToons.size && toonCount == bigToons.size) {
                        for (i in toonCount until chunkedSmallToons.size) {
                            val chunkedList = chunkedSmallToons[i].chunked(4)
                            chunkedList.forEach { chunk ->
                                Row {
                                    chunk.forEach { toon ->
                                        SmallToonCard(
                                            toon = toon,
                                            viewModel = viewModel,
                                            sizeMode = SIZE.BIG
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    } else {
        val chunkedSmallToons = smallToons.chunked(6)
        var toonCount = 0
        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth()
        ) {
            item {
                bigToons.forEach { bigToon ->
                    // Big Toon 개수가 대비 Small Toon이 부족할 경우,
                    // Small Toon 대비 Big Toon이 부족할 경우 해결 필요
                    if (toonCount >= chunkedSmallToons.size) {
                        BigToonCard(toon = bigToon, viewModel = viewModel)
                    } else {
                        DisplayCard(
                            bigToon,
                            chunkedSmallToons[toonCount],
                            viewModel,
                            sizeMode = SIZE.SMALL
                        )
                    }
                    toonCount++
                    if (bigToons.size < chunkedSmallToons.size && toonCount == bigToons.size) {
                        for (i in toonCount until chunkedSmallToons.size) {
                            val chunkedList = chunkedSmallToons[i].chunked(3)
                            chunkedList.forEach { chunk ->
                                Row {
                                    chunk.forEach { toon ->
                                        SmallToonCard(
                                            toon = toon,
                                            viewModel = viewModel,
                                            sizeMode = SIZE.SMALL
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }


}

@Composable
fun DisplayCard(
    bigToon: BigToon,
    smallToons: List<SmallToon>,
    viewModel: MainViewModel,
    sizeMode: SIZE = SIZE.SMALL
) {
    val chunkedList =
        when (sizeMode) {
            SIZE.SMALL -> smallToons.chunked(3)
            SIZE.BIG -> smallToons.chunked(4)
        }


    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BigToonCard(toon = bigToon, viewModel = viewModel, sizeMode = sizeMode)
        chunkedList.forEach { chunk ->
            Row {
                chunk.forEach { toon ->
                    SmallToonCard(toon = toon, viewModel, sizeMode = sizeMode)
                }
            }
        }
    }
}

private var isDouble = false

// ToonCard를 여러번 클릭했을 경우 WebView가 여러개 나타나는 것을 방지
@Suppress("DEPRECATION")
fun doubleClickChecker(run: () -> Unit) {
    when {
        isDouble -> {
            return
        }
    }
    run()
    isDouble = true
    Handler().postDelayed({
        isDouble = false
    }, 300)
}


@Composable
fun SmallToonCard(toon: SmallToon, viewModel: MainViewModel, sizeMode: SIZE = SIZE.SMALL) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val cardWidth = when (sizeMode) {
        SIZE.SMALL -> (screenWidth - 4) / 3
        SIZE.BIG -> (screenWidth - 95) / 4
    }
    val cardHeight = cardWidth * 2
    val wifiOption by viewModel.wifiOption.collectAsState()

    Card(
        modifier = Modifier
            .size(cardWidth.dp, cardHeight.dp)
            .padding(2.dp)
            .clickable {
                doubleClickChecker {
                    val wifiInfo = isWifiConnected(context)
                    if (wifiOption && !wifiInfo) {
                        Toast
                            .makeText(context, "Wi-Fi 연결 상태가 아닙니다.", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        val intent = Intent(context, ToonActivity::class.java)
                        intent.putExtra("toon_url", toon.toonUrl)
                        context.startActivity(intent)
                    }
                }
            }
    ) {
        var isVisible by remember { mutableStateOf(false) }
        val density = LocalDensity.current


        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.onGloballyPositioned { coordinates ->
                // Check if the composable is within the visible bounds
                val windowBounds = density.run {
                    IntOffset(
                        coordinates.localToWindow(Offset.Zero).x.toInt(),
                        coordinates.localToWindow(Offset.Zero).y.toInt()
                    )
                }
                val screenHeight = configuration.screenHeightDp

                isVisible =
                    (windowBounds.y in -(cardHeight) * 2..screenHeight + (cardHeight) * 5
                            && windowBounds.x in -(screenWidth * 2)..screenWidth * 3)
            }
        ) {
            if (isVisible) {
                Image(
                    painter = // 로딩 중에 표시될 Placeholder 이미지
                    rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current).data(data = toon.mainImage)
                            .apply(block = fun ImageRequest.Builder.() {
                                placeholder(R.drawable.splash_image) // 로딩 중에 표시될 Placeholder 이미지
                            }).build()
                    ),
                    contentDescription = "background",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}


@Composable
fun BigToonCard(toon: BigToon, viewModel: MainViewModel, sizeMode: SIZE = SIZE.SMALL) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val cardWidth = when (sizeMode) {
        SIZE.SMALL -> screenWidth - 4
        SIZE.BIG -> screenWidth - 95
    }
    val cardHeight = cardWidth * 0.7
    val wifiOption by viewModel.wifiOption.collectAsState()

    Card(
        modifier = Modifier
            .size(cardWidth.dp, cardHeight.dp)
            .padding(2.dp)
            .clickable {
                doubleClickChecker {
                    val wifiInfo = isWifiConnected(context)
                    if (wifiOption && !wifiInfo) {
                        Toast
                            .makeText(context, "Wi-Fi 연결 상태가 아닙니다.", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        val intent = Intent(context, ToonActivity::class.java)
                        intent.putExtra("toon_url", toon.toonUrl)
                        context.startActivity(intent)
                    }
                }
            }
    ) {

        var isVisible by remember { mutableStateOf(false) }
        val density = LocalDensity.current

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    // Check if the composable is within the visible bounds
                    val windowBounds = density.run {
                        IntOffset(
                            coordinates.localToWindow(Offset.Zero).x.toInt(),
                            coordinates.localToWindow(Offset.Zero).y.toInt()
                        )
                    }
                    val screenHeight = configuration.screenHeightDp

                    isVisible =
                        (windowBounds.y in -(cardHeight.toInt()) * 2..screenHeight + (cardHeight.toInt()) * 5
                                && windowBounds.x in 0..screenWidth)
                }
        ) {
            Image(
                painter =
                rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = toon.backgroundImage)
                        .build()
                ),
                contentDescription = "background",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
            )

            val gifOption by viewModel.gifOption.collectAsState()

            val gifRepeat = if (gifOption) -1 else 0

            if (toon.mainGIF != null) {
                if (isVisible) {
                    GifImage(
                        source = toon.mainGIF,
                        modifier = Modifier.fillMaxSize(),
                        repeat = gifRepeat
                    )
                }
            } else {
                Image(
                    painter = // 로딩 중에 표시될 Placeholder 이미지
                    rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current).data(data = toon.mainImage)
                            .build()
                    ),
                    contentDescription = "main",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
            }

            val screenSize by remember {
                mutableIntStateOf(configuration.screenWidthDp)
            }
            var stY = 650f
            var edY = 300f

            if (screenSize > 720) {
                stY = 1300f
                edY = 500f
            }

            val gradient = Brush.verticalGradient(
                colors = listOf(Color.Black, Color(255, 255, 255, 0)),
                startY = stY, edY,
            )
            Box(
                modifier = Modifier
                    .background(gradient)
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = // 로딩 중에 표시될 Placeholder 이미지
                        rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current).data(data = toon.titleImage)
                                .build()
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(bottom = 6.dp)
                            .width((cardWidth / 2).dp)
                    )
                    Text(
                        text = toon.subTitle.toString()
                            .replace("[", "")
                            .replace("]", ""),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.LightGray,
                        modifier = Modifier
                            .padding(bottom = 26.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SearchToonCard() {

}