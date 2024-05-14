package com.kuneosu.newcompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kuneosu.newcompose.data.room.ToonDatabase
import com.kuneosu.newcompose.viewModel.SearchViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(navController: NavController, viewModel: SearchViewModel) {
    val db = ToonDatabase.getDatabase(LocalContext.current)
    var result by remember { mutableStateOf(listOf<String>()) }
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(10.dp),
                backgroundColor = Color.Black,

                ) {
                var text by remember { mutableStateOf("") }
                val containerColor = Color(34, 34, 34)
                TextField(
                    modifier = Modifier.weight(8.5f),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = containerColor,
                        unfocusedContainerColor = containerColor,
                        disabledContainerColor = containerColor,
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color(34, 34, 34),
                        unfocusedIndicatorColor = Color(34, 34, 34),
                        disabledIndicatorColor = Color(34, 34, 34),
                        errorIndicatorColor = Color(34, 34, 34),
                    ),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,
                    placeholder = {
                        Text(text = "작품, 작가, 장르를 입력하세요.", color = Color(93, 93, 93))
                    },
                    value = text,
                    onValueChange = {
                        text = it
                        CoroutineScope(Dispatchers.Default).launch {
                            val bigToonResult = db.toonDao().searchBigToon(text)
                            val smallToonResult = db.toonDao().searchSmallToon(text)
                            viewModel.setSearchResult(bigToonResult, smallToonResult)
                            result = viewModel.searchResult
                        }
                    },
                )
                IconButton(onClick = {
                    navController.popBackStack()
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
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "검색결과",
                color = Color.LightGray,
                modifier = Modifier.padding(10.dp),
                fontSize = 14.sp
            )
            result.forEach { text ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 10.dp)
                ) {
                    Icon(
                        imageVector =
                        Icons.Default.Search,
                        contentDescription = "",
                        tint = Color.White
                    )
                    Text(
                        text = text,
                        color = Color.White,
                        modifier = Modifier.padding(10.dp),
                        fontSize = 18.sp
                    )
                }
            }
        }
    }


}

@Composable
@Preview
fun SearchPreView() {
//    SearchScreen()
}

