package com.kuneosu.newcompose.data.model

data class BigToon(
    val title: String,
    val titleImage: Int,
    val mainImage: Int? = null,
    val subTitle: String? = null,
    val backgroundImage: Int,
    val mainGIF: Int? = null,
    val toonUrl: String,
)

data class SmallToon(
    val title: String,
    val mainImage: Int? = null,
    val toonUrl: String,
)