package com.kuneosu.newcompose

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.request.repeatCount


// GIF 이미지 로더
@Composable
fun GifImage(source: Int, modifier: Modifier, contentScale: ContentScale = ContentScale.Fit) {
    val context = LocalContext.current
    val imageLoader = coil.ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(data = source)
            .apply(block = fun ImageRequest.Builder.() {
                // image 불러오는 동안 출력할 이미지
                placeholder(R.drawable.logo_square)
                // image 를 불러오는데 실패했을 때 표시할 이미지
                error(R.drawable.logo_square)
            }).repeatCount(0).build(),
        // data = 불러올 이미지
        imageLoader = imageLoader,
    )

    // image 설정
    Image(
        painter = painter,
        contentDescription = "",
        modifier = modifier,
        contentScale = contentScale,
    )

}

// 비디오 플레이어, 최초 1회 자동 재생, 재생 버튼 가림
//@OptIn(UnstableApi::class)
//@Composable
//fun VideoPlayer() {
//    val context = LocalContext.current
//    // ExoPlayer 객체 생성
//    val exoPlayer = ExoPlayer.Builder(context).build()
//    val video =
//        "https://kr-a.kakaopagecdn.com/P/C/2320/c1a/78363bcd-eec2-4d43-8045-c1f732ad87a1.webm"
//    val videoUri = Uri.parse(video)
//    // source 설정
//    val mediaSource = remember {
//        MediaItem.Builder()
//            .setUri(videoUri)
//            .build()
//    }
//    // mediaSource 가 실행되었을때
//    LaunchedEffect(mediaSource) {
//        exoPlayer.setMediaItem(mediaSource)
//        exoPlayer.prepare()
//        // 자동으로 재생
//        exoPlayer.play()
//    }
//
//    DisposableEffect(Unit) {
//        onDispose {
//            exoPlayer.release()
//        }
//    }
//
//    AndroidView(
//        factory = { ctx ->
//            PlayerView(ctx).apply {
//                player = exoPlayer
//                // 재생 버튼 등 기타 메뉴 제거
//                useController = false
//            }
//        },
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(200.dp)
//            .background(Color.White),
//    )
//}
