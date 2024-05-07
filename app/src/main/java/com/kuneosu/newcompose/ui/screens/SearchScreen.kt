package com.kuneosu.newcompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults.textFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController) {
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(10.dp),
                backgroundColor = Color.Black,

                ) {
                var text by remember { mutableStateOf("") }
                TextField(
                    modifier = Modifier.weight(8.5f),
                    colors = textFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        containerColor = Color(34, 34, 34),
                        focusedIndicatorColor = Color(34, 34, 34),
                        errorIndicatorColor = Color(34, 34, 34),
                        disabledIndicatorColor = Color(34, 34, 34),
                        unfocusedIndicatorColor = Color(34, 34, 34),
                        cursorColor = Color.White,

                        ),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,
                    placeholder = {
                        Text(text = "작품, 작가, 장르를 입력하세요.", color = Color(93, 93, 93))
                    },
                    value = text,
                    onValueChange = {
                        text = it
                    },
                )
                IconButton(onClick = {
                    navController.navigate("main_screen")
                }, modifier = Modifier.weight(1.5f)) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }
        },
    ) {
        Column(
            modifier = Modifier
                .background(Color.Black)
                .padding(paddingValues = it)
                .fillMaxSize()
        ) {

        }
    }


}

@Composable
@Preview
fun SearchPreView() {
//    SearchScreen()
}

