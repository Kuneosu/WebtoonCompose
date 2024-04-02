package com.kuneosu.newcompose

import androidx.compose.ui.graphics.Color

data class Toon(
    val titleImage: Int,
    val subTitle: String? = null,
    val mainImage: Int? = null,
    val backgroundImage: Int,
    val mainGIF: Int? = null,
)