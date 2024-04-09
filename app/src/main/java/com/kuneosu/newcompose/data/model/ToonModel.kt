package com.kuneosu.newcompose.data.model
data class ToonModel(
    val titleImage: Int,
    val subTitle: String? = null,
    val mainImage: Int? = null,
    val backgroundImage: Int,
    val mainGIF: Int? = null,
){

    companion object {
        fun fromJson(json: Map<String, Any>): ToonModel = ToonModel(
            titleImage = json["titleImage"] as Int,
            subTitle = json["subTitle"] as String,
            mainImage = json["mainImage"] as Int,
            backgroundImage = json["backgroundImage"] as Int,
            mainGIF = json["mainGIF"] as Int,
        )
    }
}




