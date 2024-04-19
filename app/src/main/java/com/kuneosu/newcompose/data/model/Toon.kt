package com.kuneosu.newcompose.data.model

data class Toon(
    val titleImage: Int,
    val subTitle: String? = null,
    val mainImage: Int? = null,
    val backgroundImage: Int,
    val mainGIF: Int? = null,
    val toonUrl: String,
)