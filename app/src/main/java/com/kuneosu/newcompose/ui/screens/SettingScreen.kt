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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kuneosu.newcompose.R
import com.kuneosu.newcompose.ui.activity.MainActivity
import com.kuneosu.newcompose.ui.theme.ThemeMode
import com.kuneosu.newcompose.util.OtherScreenBackPressed
import com.kuneosu.newcompose.viewModel.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavController, viewModel: MainViewModel) {
    val mainActivity = LocalContext.current as MainActivity
    val backPressedCallback = OtherScreenBackPressed(navController)
    mainActivity.onBackPressedDispatcher.addCallback(mainActivity, backPressedCallback.callback)

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { /*TODO*/ },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.onBackground),
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onBackground)
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
            MenuText(text = stringResource(R.string.wating_free_notify), icon = Icons.Outlined.Info)
            Spacer(modifier = Modifier.size(10.dp))
            DisableButton()

            Spacer(modifier = Modifier.size(20.dp))
            MenuText(text = stringResource(R.string.my_news), icon = Icons.Outlined.Info)
            Spacer(modifier = Modifier.size(10.dp))
            DisableButton()

            Spacer(modifier = Modifier.size(20.dp))
            MenuText(text = stringResource(R.string.notice), icon = Icons.Outlined.Info)
            Spacer(modifier = Modifier.size(10.dp))
            NoticeButton()

            Spacer(modifier = Modifier.size(20.dp))
            MenuText(text = stringResource(R.string.display_style), null)
            Spacer(modifier = Modifier.size(10.dp))
            DisplayStyle(viewModel)

            Spacer(modifier = Modifier.size(20.dp))
            MenuText(text = stringResource(R.string.work_notify), icon = null)
            Spacer(modifier = Modifier.size(10.dp))
            DisableButton()

            Spacer(modifier = Modifier.size(20.dp))
            MenuText(text = stringResource(R.string.use_config), icon = null)
            Spacer(modifier = Modifier.size(10.dp))
            UseOptions(viewModel)

            Spacer(modifier = Modifier.size(20.dp))
            AdditionalText()

            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = stringResource(R.string.copyright_kakao_webtoon),
                color = Color(63, 63, 63),
                fontSize = 13.sp
            )
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
            text = stringResource(R.string.copyright_info),
            color = Color(149, 149, 149), fontSize = 13.sp
        )
        VerticalDivider(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            color = Color(82, 82, 82)
        )
        Text(
            text = stringResource(R.string.copyright_condition),
            color = Color(82, 82, 82), fontSize = 13.sp
        )
        VerticalDivider(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            color = Color(82, 82, 82)
        )
        Text(
            text = stringResource(R.string.copyright_customer_center),
            color = Color(82, 82, 82), fontSize = 13.sp
        )
        VerticalDivider(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            color = Color(82, 82, 82)
        )
        Text(
            text = stringResource(R.string.copyright_ceo_info),
            color = Color(82, 82, 82), fontSize = 13.sp
        )
    }
}


@Composable
fun UseOptions(viewModel: MainViewModel) {
    val gifOption by viewModel.gifOption.collectAsState()
    val wifiOption by viewModel.wifiOption.collectAsState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OptionButton(
            icon = Icons.Default.CheckCircle,
            text = stringResource(R.string.option_video_auto),
            selected = gifOption,
            viewModel = viewModel
        )
        OptionButton(
            icon = Icons.Default.AddCircle,
            text = stringResource(R.string.option_wifi),
            selected = wifiOption,
            viewModel = viewModel
        )
    }
}

@Composable
fun OptionButton(
    text: String, selected: Boolean = false, icon: ImageVector, viewModel: MainViewModel
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val itemWidth = screenWidth / 2 - 12

    var containerColor = MaterialTheme.colorScheme.onSecondaryContainer
    var textColor = MaterialTheme.colorScheme.primary
    if (!selected) {
        containerColor = MaterialTheme.colorScheme.secondaryContainer
        textColor = MaterialTheme.colorScheme.secondary
    }

    Button(
        onClick = {
            when (text) {
                "영상 자동 재생" -> {
                    viewModel.setGifOption(!selected)
                }

                "Wi-Fi에서만 회차 감상" -> {
                    viewModel.setWifiOption(!selected)
                }
            }
        },
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
fun DisplayStyle(viewModel: MainViewModel) {
    val displayChoice by viewModel.displayChoice.collectAsState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        DisplayStyleButton(
            icon = Icons.Default.CheckCircle,
            text = stringResource(R.string.dark_mode),
            selected = displayChoice[0],
            viewModel = viewModel
        )
        DisplayStyleButton(
            icon = Icons.Default.AddCircle,
            text = stringResource(R.string.light_mode),
            selected = displayChoice[1],
            viewModel = viewModel
        )
        DisplayStyleButton(
            icon = Icons.Default.AccountCircle,
            text = stringResource(R.string.system_mode),
            selected = displayChoice[2],
            viewModel = viewModel
        )
    }
}

@Composable
fun DisplayStyleButton(
    icon: ImageVector,
    text: String,
    selected: Boolean = false,
    viewModel: MainViewModel
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val itemWidth = screenWidth / 3 - 10

    var containerColor = MaterialTheme.colorScheme.onSecondaryContainer
    var textColor = MaterialTheme.colorScheme.primary
    if (!selected) {
        containerColor = MaterialTheme.colorScheme.secondaryContainer
        textColor = MaterialTheme.colorScheme.secondary
    }

    Button(
        onClick = {
            when (text) {
                "다크 모드" -> {
                    viewModel.setThemeMode(ThemeMode.DARK)
                    viewModel.setDisplayChoice(booleanArrayOf(true, false, false))
                }

                "라이트 모드" -> {
                    viewModel.setThemeMode(ThemeMode.LIGHT)
                    viewModel.setDisplayChoice(booleanArrayOf(false, true, false))
                }

                "시스템 설정" -> {
                    viewModel.setThemeMode(ThemeMode.SYSTEM)
                    viewModel.setDisplayChoice(booleanArrayOf(false, false, true))
                }
            }
        },
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
            containerColor = MaterialTheme.colorScheme.onPrimary
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
                color = MaterialTheme.colorScheme.primary
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
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = stringResource(R.string.button_text_after_login),
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
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
            text = text, color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold, fontSize = 15.sp
        )
        IconButton(onClick = { /*TODO*/ }) {
            if (icon != null) {
                Icon(
                    imageVector = icon, contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary
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
            containerColor = MaterialTheme.colorScheme.onPrimary
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = "캐시 충전",
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
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
        SmallButton(text = stringResource(R.string.button_text_cash))
        SmallButton(text = stringResource(R.string.button_text_use_ticket))
        SmallButton(text = stringResource(R.string.button_text_coupon))
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
            containerColor = MaterialTheme.colorScheme.onPrimary
        ),
        shape = RoundedCornerShape(8.dp),

        ) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
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
            text = stringResource(R.string.login),
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