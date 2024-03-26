package com.kuneosu.newcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kuneosu.newcompose.ui.theme.NewComposeTheme

class ToonActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NewComposeTheme {
                val toonTitle = intent.getIntExtra("toon_title", 0)
                val toonBackground = intent.getIntExtra("toon_background", 0)
                if (intent.hasExtra("toon_main_gif")) {
                    val toonMainGif = intent.getIntExtra("toon_main_gif", 0)
                    ToonScreen(
                        toonTitle = toonTitle,
                        toonBackground = toonBackground,
                        toonMainGif = toonMainGif,
                    )
                } else if (intent.hasExtra("toon_main_image")) {
                    val toonMainImage = intent.getIntExtra("toon_main_image", 0)
                    ToonScreen(
                        toonTitle = toonTitle,
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

@Composable
fun ToonScreen(
    toonTitle: Int,
    toonBackground: Int,
    toonMainGif: Int? = null,
    toonMainImage: Int? = null
) {
    val deviceWidth = LocalConfiguration.current.screenWidthDp.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(id = toonBackground),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.Top
        ) {
            if (toonMainGif != null) {
                GifImage(
                    source = toonMainGif,
                    modifier = Modifier
                        .size(deviceWidth)
                        .padding(20.dp),
                )
            } else {
                Image(
                    painter = painterResource(id = toonMainImage!!),
                    contentDescription = "",
                    modifier = Modifier
                        .size(deviceWidth)
                        .padding(20.dp),
                    contentScale = ContentScale.Fit
                )
            }



            Image(
                painter = painterResource(id = toonTitle),
                contentDescription = ""
            )
        }
    }
}