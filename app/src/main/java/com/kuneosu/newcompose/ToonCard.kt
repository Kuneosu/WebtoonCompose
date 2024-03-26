package com.kuneosu.newcompose

import android.content.Intent
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun MakeSevenToons(toons: List<Toon>) {
    val sevenToons = toons.chunked(7)

    LazyColumn(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth()
    ) {
        item {
            sevenToons.forEach { toons ->
                OneBigSixSmall(toons = toons)
            }
        }
    }
}

@Composable
fun OneBigSixSmall(toons: List<Toon>) {
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
        Spacer(modifier = Modifier.size(30.dp))

    }
}

@Composable
fun SmallToonCard(toon: Toon) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val cardWidth = (screenWidth - 9.dp) / 3

    Card(
        modifier = Modifier
            .size(cardWidth, 280.dp)
            .padding(5.dp)
            .clickable {
                val intent = Intent(context, ToonActivity::class.java)
                intent.putExtra("toon_title", toon.titleImage)
                intent.putExtra("toon_background", toon.backgroundImage)
                intent.putExtra("toon_main_image", toon.mainImage)
                context.startActivity(intent)
            }
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter,
        ) {
            Image(
                painter = painterResource(id = toon.backgroundImage),
                contentDescription = "background",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Image(
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
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val cardWidth = screenWidth - 9.dp

    Card(
        modifier = Modifier
            .size(cardWidth, 300.dp)
            .padding(5.dp)
            .clickable {
                val intent = Intent(context, ToonActivity::class.java)
                intent.putExtra("toon_title", toon.titleImage)
                intent.putExtra("toon_background", toon.backgroundImage)
                intent.putExtra("toon_main_gif", toon.mainGIF)
                context.startActivity(intent)
            }
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = toon.backgroundImage),
                contentDescription = "background",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
            )

            if (toon.mainGIF != null) {
                GifImage(source = toon.mainGIF, modifier = Modifier.fillMaxSize())
            } else {
                Image(
                    painter = painterResource(id = toon.mainImage!!),
                    contentDescription = "main",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
            }


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