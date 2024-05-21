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
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.kuneosu.newcompose.R
import com.kuneosu.newcompose.data.model.BigToon
import com.kuneosu.newcompose.data.model.SmallToon
import com.kuneosu.newcompose.ui.ToonActivity
import com.kuneosu.newcompose.viewModel.MainViewModel

//private const val TAG = "LIFE_TRACKING"


@Composable
fun MakeToonList(bigToons: List<BigToon>, smallToons: List<SmallToon>, viewModel: MainViewModel) {
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
                    OneBigSixSmall(bigToon, chunkedSmallToons[toonCount], viewModel)
                }
                toonCount++
                if (bigToons.size < chunkedSmallToons.size && toonCount == bigToons.size) {
                    for (i in toonCount until chunkedSmallToons.size) {
                        val chunkedList = chunkedSmallToons[i].chunked(3)
                        chunkedList.forEach { chunk ->
                            Row {
                                chunk.forEach { toon ->
                                    SmallToonCard(toon = toon, viewModel = viewModel)
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
fun OneBigSixSmall(bigToon: BigToon, smallToons: List<SmallToon>, viewModel: MainViewModel) {
    val chunkedList = smallToons.chunked(3)
    Column {
        BigToonCard(toon = bigToon, viewModel = viewModel)
        chunkedList.forEach { chunk ->
            Row {
                chunk.forEach { toon ->
                    SmallToonCard(toon = toon, viewModel)
                }
            }
        }
        Spacer(modifier = Modifier.size(30.dp))

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
fun SmallToonCard(toon: SmallToon, viewModel: MainViewModel) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val cardWidth = (screenWidth - 9.dp) / 3
    val wifiOption by viewModel.wifiOption.collectAsState()

    Card(
        modifier = Modifier
            .size(cardWidth, 280.dp)
            .padding(5.dp)
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
        Box(
            contentAlignment = Alignment.BottomCenter,
        ) {
            Image(
                painter = // 로딩 중에 표시될 Placeholder 이미지
                rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = toon.mainImage)
                        .apply(block = fun ImageRequest.Builder.() {
                            placeholder(R.drawable.logo_square_dark) // 로딩 중에 표시될 Placeholder 이미지
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


@Composable
fun BigToonCard(toon: BigToon, viewModel: MainViewModel) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val cardWidth = screenWidth - 9.dp
    val wifiOption by viewModel.wifiOption.collectAsState()

    Card(
        modifier = Modifier
            .size(cardWidth, 300.dp)
            .padding(5.dp)
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
        Box(
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter =
                rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = toon.backgroundImage)
                        .apply(block = fun ImageRequest.Builder.() {
                            placeholder(R.drawable.logo_square_dark) // 로딩 중에 표시될 Placeholder 이미지
                        }).build()
                ),
                contentDescription = "background",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
            )

            val gifOption by viewModel.gifOption.collectAsState()

            val gifRepeat = if (gifOption) -1 else 0

            if (toon.mainGIF != null) {
                GifImage(
                    source = toon.mainGIF,
                    modifier = Modifier.fillMaxSize(),
                    repeat = gifRepeat
                )
            } else {
                Image(
                    painter = // 로딩 중에 표시될 Placeholder 이미지
                    rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current).data(data = toon.mainImage)
                            .apply(block = fun ImageRequest.Builder.() {
                                placeholder(R.drawable.logo_square_dark) // 로딩 중에 표시될 Placeholder 이미지
                            }).build()
                    ),
                    contentDescription = "main",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
            }

            val mainColor = Color(11, 17, 51, 255)

            val gradient = Brush.verticalGradient(
                colors = listOf(mainColor, Color(255, 255, 255, 0)),
                startY = 650f, 300f,
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
                                .apply(block = fun ImageRequest.Builder.() {
                                    placeholder(R.drawable.logo_square_dark) // 로딩 중에 표시될 Placeholder 이미지
                                }).build()
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(bottom = 6.dp)
                            .width(cardWidth / 2)
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