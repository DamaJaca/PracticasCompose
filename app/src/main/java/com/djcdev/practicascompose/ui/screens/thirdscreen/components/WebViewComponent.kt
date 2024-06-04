package com.djcdev.practicascompose.ui.screens.thirdscreen.components

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.isVisible

@Composable
fun WebViewComponent(url: String, isLoading : () ->Unit) {
    AndroidView(
        factory = { context ->

            WebView(context).apply {
                clearCache(true)
                webViewClient = object : WebViewClient(){
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        view?.isVisible=true
                        isLoading()
                    }
                }
                loadUrl(url)
                isVisible=false
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}