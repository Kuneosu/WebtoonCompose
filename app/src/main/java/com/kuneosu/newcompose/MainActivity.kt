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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
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
                val gradient =
                    Brush.horizontalGradient(listOf(Color(0xFF28D8A3), Color(0xFF00BEB2)))

                Column {
                    GradientButton(
                        text = "Gradient Button - Max Width",
                        gradient = gradient,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    GradientButton(
                        text = "Gradient Button - Wrap Width",
                        gradient = gradient,
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )

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

                    VideoPlayer()

                    GifImage()

                }
            }
        }
    }


}

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
        ImageRequest.Builder(LocalContext.current).data(data = R.drawable.image)
            .apply(block = fun ImageRequest.Builder.() {
                placeholder(R.drawable.ic_launcher_foreground)
                error(R.drawable.ic_launcher_background)
            }).build(), imageLoader = imageLoader
    )

    Image(
        painter = painter,
        contentDescription = "",
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(10.dp)),
        contentScale = ContentScale.Fit
    )
}


@OptIn(UnstableApi::class)
@Composable
fun VideoPlayer() {
    val context = LocalContext.current
    val exoPlayer = ExoPlayer.Builder(context).build()
    val video =
        "https://kr-a.kakaopagecdn.com/P/C/2320/c1a/78363bcd-eec2-4d43-8045-c1f732ad87a1.webm"
    val videoUri = Uri.parse(video)
    val mediaSource = remember {
        androidx.media3.common.MediaItem.Builder()
            .setUri(videoUri)
            .build()
    }


    LaunchedEffect(mediaSource) {
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.prepare()
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
                useController = false
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.White),
    )


}

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