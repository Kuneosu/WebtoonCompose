package com.kuneosu.newcompose.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kuneosu.newcompose.R
import com.kuneosu.newcompose.ui.theme.ThemeMode
import com.kuneosu.newcompose.viewModel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(navController: NavController, viewModel: MainViewModel) {
    val themeMode by viewModel.themeMode.collectAsState()
    val logo = when (themeMode) {
        ThemeMode.DARK -> R.drawable.logo_square_dark
        ThemeMode.LIGHT -> R.drawable.logo_square_light
        ThemeMode.SYSTEM -> if (isSystemInDarkTheme()) R.drawable.logo_square_dark else R.drawable.logo_square_light
    }


    CenterAlignedTopAppBar(
        modifier = Modifier
            .height(50.dp),
        title = {
            Image(
                painter = painterResource(id = logo),
                contentDescription = "",
                modifier = Modifier
                    .height(50.dp)
                    .padding(vertical = 16.dp)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            MaterialTheme.colorScheme.background
        ),
        navigationIcon = (
                {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.MailOutline,
                            contentDescription = stringResource(R.string.mail),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }),
        actions = {
            IconButton(onClick = {
                navController.navigate("search_screen")
            }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(R.string.search),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            IconButton(onClick = {
                navController.navigate("setting_screen")
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.List,
                    contentDescription = stringResource(R.string.menu),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}