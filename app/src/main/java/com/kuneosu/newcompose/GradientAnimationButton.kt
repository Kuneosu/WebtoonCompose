package com.kuneosu.newcompose

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


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
        shape = RoundedCornerShape(16.dp),
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.White,
            modifier = Modifier
                .background(brush)
                .padding(horizontal = 24.dp, vertical = 12.dp)
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

