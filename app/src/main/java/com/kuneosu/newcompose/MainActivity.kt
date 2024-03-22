package com.kuneosu.newcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.kuneosu.newcompose.ui.theme.NewComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NewComposeTheme {

                Scaffold(
                    topBar = ({
                        MainTopBar()
                    }),
                    content = {
                        Column (
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it)
                                .background(Color.Black)
                        ){
                            MainContent()
                        }
                    })

            }
        }
    }
}


@Composable
fun MainContent() {
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
//                        GradientAnimationButton(
//                            text = "오늘 연재무료",
//                            startColor = Color(255, 193, 7, 255),
//                            endColor = Color(14, 228, 255, 255)
//                        )
    }

    val toonList = DataProvider.toonList
    MakeSevenToons(toons = toonList)
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    NewComposeTheme {
        GradientAnimationButton("버튼", Color(0xFF28D8A3), Color(0xFF00BEB2))
    }
}