package com.kuneosu.newcompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavController) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { /*TODO*/ },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("main_screen")
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black),
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues = it)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LoginText()

            Spacer(modifier = Modifier.size(10.dp))
            CashButton()

            Spacer(modifier = Modifier.size(5.dp))
            ThreeButtons()

            Spacer(modifier = Modifier.size(20.dp))
            MenuText(text = "기다무 알림", icon = Icons.Outlined.Info)
            Spacer(modifier = Modifier.size(10.dp))
            DisableButton()

            Spacer(modifier = Modifier.size(20.dp))
            MenuText(text = "내 소식", icon = Icons.Outlined.Info)
            Spacer(modifier = Modifier.size(10.dp))
            DisableButton()

            Spacer(modifier = Modifier.size(20.dp))
            MenuText(text = "공지사항", icon = Icons.Outlined.Info)
            Spacer(modifier = Modifier.size(10.dp))
            NoticeButton()

            Spacer(modifier = Modifier.size(20.dp))
            MenuText(text = "화면 스타일", null)
            Spacer(modifier = Modifier.size(10.dp))
            DisplayStyle()

            Spacer(modifier = Modifier.size(20.dp))
            MenuText(text = "작품 알림", icon = null)
            Spacer(modifier = Modifier.size(10.dp))
            DisableButton()

            Spacer(modifier = Modifier.size(20.dp))
            MenuText(text = "사용 환경", icon = null)
            Spacer(modifier = Modifier.size(10.dp))
            UseOptions()

            Spacer(modifier = Modifier.size(20.dp))
            AdditionalText()

            Spacer(modifier = Modifier.size(10.dp))
            Text(text = "© KAKAO WEBTOON", color = Color(63, 63, 63), fontSize = 13.sp)
        }
    }
}

@Composable
fun AdditionalText() {
    Row(
        modifier = Modifier.height(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "개인정보처리방침",
            color = Color(149, 149, 149), fontSize = 13.sp
        )
        VerticalDivider(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            color = Color(82, 82, 82)
        )
        Text(
            text = "이용약관",
            color = Color(82, 82, 82), fontSize = 13.sp
        )
        VerticalDivider(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            color = Color(82, 82, 82)
        )
        Text(
            text = "고객센터",
            color = Color(82, 82, 82), fontSize = 13.sp
        )
        VerticalDivider(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            color = Color(82, 82, 82)
        )
        Text(
            text = "사업자 정보",
            color = Color(82, 82, 82), fontSize = 13.sp
        )
    }
}


@Composable
fun UseOptions() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OptionButton(icon = Icons.Default.CheckCircle, text = "영상 자동 재생", selected = true)
        OptionButton(icon = Icons.Default.AddCircle, text = "Wi-Fi에서만 회차 감상")
    }
}

@Composable
fun OptionButton(
    text: String, selected: Boolean = false, icon: ImageVector
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val itemWidth = screenWidth / 2 - 12

    var containerColor = Color(34, 34, 34)
    var textColor = Color.White
    if (!selected) {
        containerColor = Color(22, 22, 22)
        textColor = Color(94, 94, 94)
    }

    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier.width(itemWidth.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon, contentDescription = "",
                modifier = Modifier.size(34.dp),
                tint = textColor
            )
            Spacer(modifier = Modifier.size(5.dp))
            Text(text = text, fontSize = 12.sp, color = textColor)
        }
    }
}

@Composable
fun DisplayStyle() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        DisplayStyleButton(icon = Icons.Default.CheckCircle, text = "다크 모드", selected = true)
        DisplayStyleButton(icon = Icons.Default.AddCircle, text = "라이트 모드")
        DisplayStyleButton(icon = Icons.Default.AccountCircle, text = "시스템 설정")
    }
}

@Composable
fun DisplayStyleButton(icon: ImageVector, text: String, selected: Boolean = false) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val itemWidth = screenWidth / 3 - 10

    var containerColor = Color(34, 34, 34)
    var textColor = Color.White
    if (!selected) {
        containerColor = Color(22, 22, 22)
        textColor = Color(94, 94, 94)
    }

    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier.width(itemWidth.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon, contentDescription = "",
                modifier = Modifier.size(34.dp),
                tint = textColor
            )
            Spacer(modifier = Modifier.size(5.dp))
            Text(text = text, fontSize = 12.sp, color = textColor)
        }
    }
}

@Composable
fun NoticeButton() {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(34, 34, 34)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "5월 유료화 & 종료 작품 관련 안내",
            )
            Text(
                text = "2024.04.30",
                color = Color(91, 91, 91),
                fontSize = 13.sp
            )
        }

    }
}


@Composable
fun DisableButton() {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(22, 22, 22)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = "로그인 후 이용 가능합니다.",
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(76, 76, 76)
        )
    }
}

@Composable
fun MenuText(
    text: String,
    icon: ImageVector?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text, color = Color.White,
            fontWeight = FontWeight.Bold, fontSize = 15.sp
        )
        IconButton(onClick = { /*TODO*/ }) {
            if (icon != null) {
                Icon(
                    imageVector = icon, contentDescription = "",
                    tint = Color.White
                )
            }
        }
    }
}


@Composable
fun CashButton() {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(34, 34, 34)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = "캐시 충전",
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ThreeButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SmallButton(text = "캐시 내역")
        SmallButton(text = "이용권 내역")
        SmallButton(text = "쿠폰 등록")
    }
}

@Composable
fun SmallButton(text: String) {
    val screenWidth = LocalConfiguration.current.screenWidthDp - 30
    val buttonWidth = screenWidth / 3
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier.width(buttonWidth.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(34, 34, 34)
        ),
        shape = RoundedCornerShape(8.dp),

        ) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun LoginText() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.CheckCircle, contentDescription = "",
            tint = Color(95, 95, 95),
            modifier = Modifier.size(34.dp)
        )
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            text = "Login",
            color = Color(95, 95, 95),
            fontSize = 45.sp
        )
    }
}


@Composable
@Preview(heightDp = 2000)
fun PreViewSetting() {
//    SettingScreen()
}