package com.kuneosu.newcompose.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "big_toon")
data class BigToon(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "main_image") val mainImage: String? = null,
    @ColumnInfo(name = "title_image") val titleImage: String,
    @ColumnInfo(name = "sub_title") val subTitle: String? = null,
    @ColumnInfo(name = "background_image") val backgroundImage: String,
    @ColumnInfo(name = "main_gif") val mainGIF: String? = null,
    @ColumnInfo(name = "toon_url") val toonUrl: String,
)

@Entity(tableName = "small_toon")
data class SmallToon(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "main_image") val mainImage: String,
    @ColumnInfo(name = "toon_url") val toonUrl: String,
)