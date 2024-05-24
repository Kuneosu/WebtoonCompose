package com.kuneosu.newcompose.ui.screens

//noinspection UsingMaterialAndMaterial3Libraries
import android.content.Context
import android.content.Intent
import android.os.Handler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kuneosu.newcompose.data.room.ToonDatabase
import com.kuneosu.newcompose.ui.activity.MainActivity
import com.kuneosu.newcompose.ui.activity.ToonActivity
import com.kuneosu.newcompose.util.OtherScreenBackPressed
import com.kuneosu.newcompose.viewModel.SearchViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private var isDouble = false

@Suppress("DEPRECATION")
fun doubleClickChecker(run: () -> Unit) {
    when {
        isDouble -> {
            return
        }
    }
    run()
    isDouble = true
    Handler().postDelayed({
        isDouble = false
    }, 300)
}

@Composable
fun SearchScreen(navController: NavController, viewModel: SearchViewModel) {
    val db = ToonDatabase.getDatabase(LocalContext.current)
//    var result by remember { mutableStateOf(listOf<String>()) }
    val mainActivity = LocalContext.current as MainActivity
    val backPressedCallback = OtherScreenBackPressed(navController)
    mainActivity.onBackPressedDispatcher.addCallback(mainActivity, backPressedCallback.callback)


    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .padding(10.dp),
                backgroundColor = MaterialTheme.colorScheme.background,
                elevation = 0.dp

            ) {
//                var text by remember { mutableStateOf("") }
                var text by remember { mutableStateOf(TextFieldValue("")) }
                val containerColor = if (MaterialTheme.colorScheme.background == Color.Black) {
                    Color(34, 34, 34)
                } else {
                    Color(238, 238, 238)
                }

                TextField(
                    modifier = Modifier.weight(8.5f),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = MaterialTheme.colorScheme.primary,
                        unfocusedTextColor = MaterialTheme.colorScheme.primary,
                        focusedContainerColor = containerColor,
                        unfocusedContainerColor = containerColor,
                        disabledContainerColor = containerColor,
                        cursorColor = MaterialTheme.colorScheme.primary,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                    ),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,
                    placeholder = {
                        Text(text = "작품, 작가, 장르를 입력하세요.", color = Color(93, 93, 93))
                    },
                    value = text,
                    onValueChange = { newText ->
                        text = newText
                        viewModel.searchToons(newText.text, db)
//                        text = it
//                        CoroutineScope(Dispatchers.Default).launch {
//                            val bigToonResult = db.toonDao().searchBigToon(text)
//                            val smallToonResult = db.toonDao().searchSmallToon(text)
//                            viewModel.setSearchResult(bigToonResult, smallToonResult)
//                            result = viewModel.searchResult
//                        }
                    },
                    trailingIcon = {
                        if (text.text.isNotEmpty()) {
                            IconButton(onClick = {
                                text = TextFieldValue("")
                                viewModel.searchToons("", db)
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Clear text",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                )
                IconButton(onClick = {
                    doubleClickChecker {
                        navController.popBackStack()
                    }
                }, modifier = Modifier.weight(1.5f)) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        },
    ) {
        val context = LocalContext.current
        val result by viewModel.searchResult.collectAsState()

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(it)
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .clickable {
                            searchItemClickListener(text, db, context)
                        }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = text,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(10.dp),
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

fun searchItemClickListener(text: String, db: ToonDatabase, context: Context) {
    doubleClickChecker {
        if (text.isNotEmpty()) {
            CoroutineScope(Dispatchers.Default).launch {
                val bigUrl: String? = db
                    .toonDao()
                    .searchBigToonUrl(text)
                val smallUrl: String? = db
                    .toonDao()
                    .searchSmallToonUrl(text)


                if (bigUrl != null) {
                    val intent = Intent(context, ToonActivity::class.java)
                    intent.putExtra("toon_url", bigUrl)
                    context.startActivity(intent)
                } else if (smallUrl != null) {
                    val intent = Intent(context, ToonActivity::class.java)
                    intent.putExtra("toon_url", smallUrl)
                    context.startActivity(intent)
                }
            }
        }
    }
}


