package com.djcdev.practicascompose.ui.companioncomposables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import com.djcdev.practicascompose.ui.theme.PracticasComposeTheme

@Composable
fun ComposeStructure (topAppBar : @Composable () ->Unit, contentApp : @Composable (PaddingValues) ->Unit){
    val statusBarHeight = with(LocalDensity.current) { WindowInsets.statusBars.getTop(LocalDensity.current).toDp() }
    PracticasComposeTheme {
        Surface (color = MaterialTheme.colorScheme.background) {
            Scaffold (
                modifier = Modifier.padding(top=statusBarHeight),
                topBar = {  topAppBar() }
            ){
                contentApp(it)
            }
        }
    }
}