package com.kuneosu.newcompose

import android.media.Image
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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
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
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.kuneosu.newcompose.ui.theme.NewComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toon = Toon(
            title = "픽 미 업!",
            author = listOf("조우네", "와삭바삭", "헤르모드"),
            mainImage = R.drawable.toon1,
            backgroundImage = R.drawable.toon1back
        )
        setContent {
            NewComposeTheme {
                Column {
                    BigToonCard(toon = toon)
                }


            }
        }
    }
}


@Composable
fun BigToonCard(toon: Toon) {
    Card(
        modifier = Modifier
            .size(400.dp)
            .padding(10.dp)
    ) {
        Box {
            androidx.compose.foundation.Image(
                painter = painterResource(id = toon.backgroundImage),
                contentDescription = "background",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop)

//            androidx.compose.foundation.Image(
//                painter = painterResource(id = toon.mainImage),
//                contentDescription ="main",
//                modifier = Modifier
//                    .fillMaxSize(),
//                contentScale = ContentScale.Fit)
            GifImage()
        }



    }
}

// GIF 이미지 로더
@Composable
fun GifImage(
) {
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
        ImageRequest.Builder(LocalContext.current).data(data = R.drawable.image)
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
            .fillMaxSize()
            .clip(RoundedCornerShape(10.dp)),
        contentScale = ContentScale.Fit
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

data class Toon(
    val title: String,
    val author: List<String>,
    val mainImage: Int,
    val backgroundImage: Int,
)


@Preview(showBackground = true)
@Composable
fun Preview() {
    NewComposeTheme {
        Column {
            GradientAnimationButton(
                text = "지금 핫한",
                startColor = Color(0xFF00BCD4),
                endColor = Color(0xFF673AB7)
            )
            GradientAnimationButton(
                text = "실시간 랭킹",
                startColor = Color(0xFFE91E63),
                endColor = Color(0xFFFF9800)
            )
            GradientAnimationButton(
                text = "오늘 연재무료",
                startColor = Color(0xFFFFC107),
                endColor = Color(0xFF00BEB2)
            )

            GifImage()
        }
    }
}