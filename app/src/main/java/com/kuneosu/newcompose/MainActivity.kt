package com.kuneosu.newcompose

import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.OptIn
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.kuneosu.newcompose.ui.theme.NewComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NewComposeTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(34, 34, 34, 255))
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        GradientAnimationButton(
                            text = "실시간 랭킹",
                            startColor = Color(255, 235, 59, 255),
                            endColor = Color(233, 30, 99, 255)
                        )
                        GradientAnimationButton(
                            text = "지금 핫한",
                            startColor = Color(156, 39, 176, 255),
                            endColor = Color(0, 188, 212, 255)
                        )
                        GradientAnimationButton(
                            text = "오늘 연재무료",
                            startColor = Color(255, 193, 7, 255),
                            endColor = Color(14, 228, 255, 255)
                        )
                    }

                    val toonList = DataProvider.toonList
                    val sevenToons = toonList.chunked(7)


                    LazyColumn {
                        sevenToons.forEach { toons ->
                            item { ToonColumn(toons = toons) }
                        }
                    }
                }


            }
        }
    }
}

@Composable
fun ToonColumn(toons: List<Toon>) {
    val firstToon = toons[0]
    val otherToons = toons.subList(1, toons.size)
    val chunkedList = otherToons.chunked(3)

    Column {
        BigToonCard(toon = firstToon)
        chunkedList.forEach { chunk ->
            Row {
                chunk.forEach { toon ->
                    SmallToonCard(toon = toon)
                }
            }
        }
    }
}

@Composable
fun SmallToonCard(toon: Toon) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val cardWidth = (screenWidth - 9.dp) / 3

    Card(
        modifier = Modifier
            .size(cardWidth, 280.dp)
            .padding(5.dp)
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter,
        ) {
            androidx.compose.foundation.Image(
                painter = painterResource(id = toon.backgroundImage),
                contentDescription = "background",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            androidx.compose.foundation.Image(
                painter = painterResource(id = toon.mainImage!!),
                contentDescription = "main",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            val gradient = Brush.verticalGradient(
                colors = listOf(toon.mainColor, Color(255, 255, 255, 0)),
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
                        painter = painterResource(id = toon.titleImage),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun BigToonCard(toon: Toon) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val cardWidth = screenWidth - 9.dp

    Card(
        modifier = Modifier
            .size(cardWidth, 300.dp)
            .padding(5.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.foundation.Image(
                painter = painterResource(id = toon.backgroundImage),
                contentDescription = "background",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
            )

            GifImage(source = toon.mainGIF!!)

            val gradient = Brush.verticalGradient(
                colors = listOf(toon.mainColor, Color(255, 255, 255, 0)),
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
                        painter = painterResource(id = toon.titleImage),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .padding(bottom = 6.dp)
                            .fillMaxWidth()
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
fun ToonMask(toon: Toon) {
    val gradient = Brush.verticalGradient(
        colors = listOf(toon.mainColor, Color(255, 255, 255, 0)),
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
                painter = painterResource(id = toon.titleImage),
                contentDescription = "",
                modifier = Modifier
                    .padding(bottom = 6.dp)

            )
            Text(
                text = toon.subTitle.toString(),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(bottom = 26.dp)
            )
        }
    }
}


// GIF 이미지 로더
@Composable
fun GifImage(source: Int) {
    val context = LocalContext.current
    val imageLoader = coil.ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    val painter = rememberAsyncImagePainter(
        // data = 불러올 이미지
        ImageRequest.Builder(LocalContext.current).data(data = source)
            .apply(block = fun ImageRequest.Builder.() {
                // image 불러오는 동안 출력할 이미지
                placeholder(R.drawable.ic_launcher_foreground)
                // image 를 불러오는데 실패했을 때 표시할 이미지
                error(R.drawable.ic_launcher_background)
            }).build(), imageLoader = imageLoader
    )

    // image 설정
    Image(
        painter = painter,
        contentDescription = "",
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

// 비디오 플레이어, 최초 1회 자동재생, 재생 버튼 가림
@OptIn(UnstableApi::class)
@Composable
fun VideoPlayer() {
    val context = LocalContext.current
    // ExoPlayer 객체 생성
    val exoPlayer = ExoPlayer.Builder(context).build()
    val video =
        "https://kr-a.kakaopagecdn.com/P/C/2320/c1a/78363bcd-eec2-4d43-8045-c1f732ad87a1.webm"
    val videoUri = Uri.parse(video)
    // source 설정
    val mediaSource = remember {
        androidx.media3.common.MediaItem.Builder()
            .setUri(videoUri)
            .build()
    }
    // mediaSource 가 실행되었을때
    LaunchedEffect(mediaSource) {
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.prepare()
        // 자동으로 재생
        exoPlayer.play()
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    AndroidView(
        factory = { ctx ->
            PlayerView(ctx).apply {
                player = exoPlayer
                // 재생 버튼 등 기타 메뉴 제거
                useController = false
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.White),
    )


}


// 그라데이션 + 애니메이션 버튼
// Parameter
// - text : 버튼 글자
// - startColor : 그라데이션 시작 색상
// - endColor : 그라데이션 종료 색상
@Composable
fun GradientAnimationButton(text: String, startColor: Color, endColor: Color) {
    val currentFontSizePx = with(LocalDensity.current) { 100.dp.toPx() } / 2
    val currentFontSizeDoublePx = currentFontSizePx * 2

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = currentFontSizeDoublePx,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
        ), label = ""
    )

    val brush = Brush.linearGradient(
        colors = listOf(startColor, endColor),
        start = Offset(offset, offset),
        end = Offset(offset + currentFontSizePx, offset + currentFontSizePx),
        tileMode = TileMode.Mirror,
    )

    Surface(
        modifier = Modifier
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = Color.White,
            modifier = Modifier
                .background(brush)
                .padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}

// 애니메이션이 없는 그라데이션 버튼
// Parameter
// - text : 버튼에 입력될 문자열
// - gradient : 그라데이션 색상을 지정하는 Brush 객체.
// -- Brush Ex : val gradient =
//                    Brush.horizontalGradient(listOf(Color(0xFF28D8A3), Color(0xFF00BEB2)))
// - modifier : 버튼의 형태를 설정하는 Modifier 객체
// - onClick : 버튼 클릭 이벤트
@Composable
fun GradientButton(
    text: String,
    gradient: Brush,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(),
        onClick = { onClick() },
    ) {
        Box(
            modifier = Modifier
                .background(gradient)
                .then(modifier),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = text)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    NewComposeTheme {
    }
}