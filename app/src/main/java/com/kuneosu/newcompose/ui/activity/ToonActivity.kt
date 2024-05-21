package com.kuneosu.newcompose.ui.activity


import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.kuneosu.newcompose.ui.theme.NewComposeTheme
import com.kuneosu.newcompose.ui.theme.ThemeMode

class ToonActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewComposeTheme(ThemeMode.SYSTEM) {
                val toonUrl = intent.getStringExtra("toon_url")
                ToonPageWebView(toonUrl!!)
            }
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun ToonPageWebView(toonUrl: String) {

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    webViewClient = WebViewClient()

                    settings.loadWithOverviewMode = true
                    settings.useWideViewPort = true
                    settings.setSupportZoom(true)
                }
            },
            update = { webView ->
                webView.loadUrl(toonUrl)
            },
            modifier = Modifier
                .fillMaxSize()
        )
    }
}