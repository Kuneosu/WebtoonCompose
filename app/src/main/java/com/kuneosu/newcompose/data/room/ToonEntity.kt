package com.kuneosu.newcompose.data.room

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class ToonEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title_image") val titleImage: Int,
    @ColumnInfo(name = "sub_title") val subTitle: String? = null,
    @ColumnInfo(name = "main_image") val mainImage: Int? = null,
    @ColumnInfo(name = "background_image") val backgroundImage: Int,
    @ColumnInfo(name = "main_gif") val mainGIF: Int? = null,
    @ColumnInfo(name = "toon_url") val toonUrl: String,
)

