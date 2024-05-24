package com.kuneosu.newcompose.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun GifButton(text: String) {
    val painter =
        rememberAsyncImagePainter("https://blogfiles.pstatic.net/MjAxODA1MjlfMjM5/MDAxNTI3NTcwODY5NDYy.onNdjZ4zooEkKMYByEQlyEF5SZutVCD2dE2mJXEpQckg.9aPUUYDqn8PFXwKc0c_KdIzv-oB_V2JpeItqo1jo7Pkg.GIF.rlfjrl24/%EB%B2%9A%EA%BD%83_%281%29.gif")

    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Surface(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.matchParentSize()
        ) {
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = androidx.compose.ui.graphics.Color.White,
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 12.dp)
            )
        }
    }
}