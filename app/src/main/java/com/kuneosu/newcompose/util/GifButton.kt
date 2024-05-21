package com.kuneosu.newcompose.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun GifButton(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center
    ) {
//        GifImage(
//            source = "https://i.pinimg.com/originals/a3/33/25/a3332514e980446f5db4ab34e59eee18.gif",
//            modifier = Modifier
//                .fillMaxSize()
//                .border(1.dp, Color.Red)
//                .background(Color.Red),
//            repeat = -1,
//            contentScale = ContentScale.Fit
//        )
        AsyncImage(
            model = "https://cdn.kqed.org/wp-content/uploads/sites/35/2024/03/iStock-501441083-1020x680.jpg",
            contentDescription = "",
            modifier = Modifier
                .padding(vertical = 12.dp),
            contentScale = ContentScale.FillHeight
        )
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.White,
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 12.dp)
                .background(Color.Transparent)
        )
    }
}