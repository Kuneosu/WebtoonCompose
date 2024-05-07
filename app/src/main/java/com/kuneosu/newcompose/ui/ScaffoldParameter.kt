package com.kuneosu.newcompose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kuneosu.newcompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(navController: NavController) {
    // 상단바
    CenterAlignedTopAppBar(
        modifier = Modifier
            .height(50.dp),
        title = {
            Image(
                painter = painterResource(id = R.drawable.logo_square),
                contentDescription = "",
                modifier = Modifier
                    .height(50.dp)
                    .padding(vertical = 16.dp)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            Color.Black
        ),
        navigationIcon = (
                {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.MailOutline,
                            contentDescription = stringResource(R.string.mail),
                            tint = Color.White
                        )
                    }
                }),
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(R.string.search),
                    tint = Color.White
                )
            }
            IconButton(onClick = {
                navController.navigate("setting_screen")
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.List,
                    contentDescription = stringResource(R.string.menu),
                    tint = Color.White
                )
            }
        }
    )
}