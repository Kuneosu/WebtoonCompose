package com.kuneosu.newcompose

import androidx.compose.ui.graphics.Color

object DataProvider {
    val toonList = listOf(
        Toon(
            titleImage = R.drawable.toon_1_title,
            mainGIF = R.drawable.toon_1_main,
            subTitle = "현군, 장성락(REDICE STUDIO), 추공",
            backgroundImage = R.drawable.toon_1_background,
            mainColor = Color(11, 19, 38)
        ),
        Toon(
            titleImage = R.drawable.toon_2_title,
            mainImage = R.drawable.toon_2_main,
            subTitle = "하리, 최팔호, 에시라",
            backgroundImage = R.drawable.toon_2_background,
            mainColor = Color(139, 122, 123)
        ),
        Toon(
            titleImage = R.drawable.toon_3_title,
            mainImage = R.drawable.toon_3_main,
            subTitle = "조우네, 와삭바삭, 헤르모드",
            backgroundImage = R.drawable.toon_3_background,
            mainColor = Color(39, 46, 69)
        ),
        Toon(
            titleImage = R.drawable.toon_4_title,
            mainImage = R.drawable.toon_4_main,
            subTitle = "",
            backgroundImage = R.drawable.toon_4_background,
            mainColor = Color(141, 141, 177)
        ),
        Toon(
            titleImage = R.drawable.toon_6_title,
            mainImage = R.drawable.toon_6_main,
            subTitle = "",
            backgroundImage = R.drawable.toon_6_background,
            mainColor = Color(69, 71, 50)
        ),
        Toon(
            titleImage = R.drawable.toon_8_title,
            mainImage = R.drawable.toon_8_main,
            subTitle = "",
            backgroundImage = R.drawable.toon_8_background,
            mainColor = Color(36, 45, 76)
        ),
        Toon(
            titleImage = R.drawable.toon_9_title,
            mainImage = R.drawable.toon_9_main,
            subTitle = "",
            backgroundImage = R.drawable.toon_9_background,
            mainColor = Color(13, 13, 13)
        )
    )

}


//object DataProvider {
//    val toonList = listOf(
//        Toon(
//            title = R.drawable.toontitle,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.toon1,
//            backgroundImage = R.drawable.toon1back,
//            mainColor = Color(43, 50, 71, 255)
//        ),
//        Toon(
//            title = R.drawable.toon1title,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.ic_launcher_foreground,
//            backgroundImage = R.drawable.ic_launcher_background,
//            mainColor = Color(80, 224, 144, 255)
//        ),
//        Toon(
//            title = R.drawable.toon2title,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.toon2image,
//            backgroundImage = R.drawable.toon2back,
//            mainColor = Color(255, 152, 0, 255)
//        ),
//        Toon(
//            title = R.drawable.toon1title,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.ic_launcher_foreground,
//            backgroundImage = R.drawable.ic_launcher_background,
//            mainColor = Color(80, 224, 144, 255)
//        ),
//        Toon(
//            title = R.drawable.toontitle,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.toon1,
//            backgroundImage = R.drawable.toon1back,
//            mainColor = Color(43, 50, 71, 255)
//        ),
//        Toon(
//            title = R.drawable.toon2title,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.toon2image,
//            backgroundImage = R.drawable.toon2back,
//            mainColor = Color(255, 152, 0, 255)
//        ),
//        Toon(
//            title = R.drawable.toontitle,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.toon1,
//            backgroundImage = R.drawable.toon1back,
//            mainColor = Color(43, 50, 71, 255)
//        ),
//        Toon(
//            title = R.drawable.toon1title,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.ic_launcher_foreground,
//            backgroundImage = R.drawable.ic_launcher_background,
//            mainColor = Color(80, 224, 144, 255)
//        ),
//        Toon(
//            title = R.drawable.toon2title,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.toon2image,
//            backgroundImage = R.drawable.toon2back,
//            mainColor = Color(255, 152, 0, 255)
//        ),
//        Toon(
//            title = R.drawable.toon1title,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.ic_launcher_foreground,
//            backgroundImage = R.drawable.ic_launcher_background,
//            mainColor = Color(80, 224, 144, 255)
//        ),
//        Toon(
//            title = R.drawable.toontitle,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.toon1,
//            backgroundImage = R.drawable.toon1back,
//            mainColor = Color(43, 50, 71, 255)
//        ),
//        Toon(
//            title = R.drawable.toon1title,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.ic_launcher_foreground,
//            backgroundImage = R.drawable.ic_launcher_background,
//            mainColor = Color(80, 224, 144, 255)
//        ),
//        Toon(
//            title = R.drawable.toon2title,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.toon2image,
//            backgroundImage = R.drawable.toon2back,
//            mainColor = Color(255, 152, 0, 255)
//        ),
//        Toon(
//            title = R.drawable.toon1title,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.ic_launcher_foreground,
//            backgroundImage = R.drawable.ic_launcher_background,
//            mainColor = Color(80, 224, 144, 255)
//        ),
//        Toon(
//            title = R.drawable.toontitle,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.toon1,
//            backgroundImage = R.drawable.toon1back,
//            mainColor = Color(43, 50, 71, 255)
//        ),
//        Toon(
//            title = R.drawable.toon2title,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.toon2image,
//            backgroundImage = R.drawable.toon2back,
//            mainColor = Color(255, 152, 0, 255)
//        ),
//        Toon(
//            title = R.drawable.toontitle,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.toon1,
//            backgroundImage = R.drawable.toon1back,
//            mainColor = Color(43, 50, 71, 255)
//        ),
//        Toon(
//            title = R.drawable.toon1title,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.ic_launcher_foreground,
//            backgroundImage = R.drawable.ic_launcher_background,
//            mainColor = Color(80, 224, 144, 255)
//        ),
//        Toon(
//            title = R.drawable.toon2title,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.toon2image,
//            backgroundImage = R.drawable.toon2back,
//            mainColor = Color(255, 152, 0, 255)
//        ),
//        Toon(
//            title = R.drawable.toon1title,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.ic_launcher_foreground,
//            backgroundImage = R.drawable.ic_launcher_background,
//            mainColor = Color(80, 224, 144, 255)
//        ),
//        Toon(
//            title = R.drawable.toontitle,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.toon1,
//            backgroundImage = R.drawable.toon1back,
//            mainColor = Color(43, 50, 71, 255)
//        ),
//        Toon(
//            title = R.drawable.toon1title,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.ic_launcher_foreground,
//            backgroundImage = R.drawable.ic_launcher_background,
//            mainColor = Color(80, 224, 144, 255)
//        ),
//        Toon(
//            title = R.drawable.toon2title,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.toon2image,
//            backgroundImage = R.drawable.toon2back,
//            mainColor = Color(255, 152, 0, 255)
//        ),
//        Toon(
//            title = R.drawable.toon1title,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.ic_launcher_foreground,
//            backgroundImage = R.drawable.ic_launcher_background,
//            mainColor = Color(80, 224, 144, 255)
//        ),
//        Toon(
//            title = R.drawable.toontitle,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.toon1,
//            backgroundImage = R.drawable.toon1back,
//            mainColor = Color(43, 50, 71, 255)
//        ),
//        Toon(
//            title = R.drawable.toon2title,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.toon2image,
//            backgroundImage = R.drawable.toon2back,
//            mainColor = Color(255, 152, 0, 255)
//        ),
//        Toon(
//            title = R.drawable.toontitle,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.toon1,
//            backgroundImage = R.drawable.toon1back,
//            mainColor = Color(43, 50, 71, 255)
//        ),
//        Toon(
//            title = R.drawable.toon1title,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.ic_launcher_foreground,
//            backgroundImage = R.drawable.ic_launcher_background,
//            mainColor = Color(80, 224, 144, 255)
//        ),
//        Toon(
//            title = R.drawable.toon2title,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.toon2image,
//            backgroundImage = R.drawable.toon2back,
//            mainColor = Color(255, 152, 0, 255)
//        ),
//        Toon(
//            title = R.drawable.toon1title,
//            author = listOf("조우네", "와삭바삭", "헤르모드"),
//            mainImage = R.drawable.ic_launcher_foreground,
//            backgroundImage = R.drawable.ic_launcher_background,
//            mainColor = Color(80, 224, 144, 255)
//        ),
//    )
//}