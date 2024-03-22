package com.kuneosu.newcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuneosu.newcompose.ui.theme.NewComposeTheme
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NewComposeTheme {

                MainScreen()

            }
        }
    }
}

@Composable
internal fun MainScreen(
) {
    val state = rememberCollapsingToolbarScaffoldState()

    CollapsingToolbarScaffold(
        modifier = Modifier
            .fillMaxSize(),
        state = state,
        scrollStrategy = ScrollStrategy.EnterAlways,
        toolbar = {
            MainTopBar()
        }) {

        MainContent()


    }

}

@Composable
fun MainContent() {
    val toonList = DataProvider.toonList
    val scrollState = rememberScrollState()
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .horizontalScroll(scrollState),
            horizontalArrangement = Arrangement.SpaceAround,
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
        MakeSevenToons(toons = toonList)
    }


}


@Preview(showBackground = true)
@Composable
fun Preview() {
    NewComposeTheme {
        GradientAnimationButton("버튼", Color(0xFF28D8A3), Color(0xFF00BEB2))
    }
}