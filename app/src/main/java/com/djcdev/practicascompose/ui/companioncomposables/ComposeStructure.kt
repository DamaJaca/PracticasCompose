package com.djcdev.practicascompose.ui.companioncomposables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.djcdev.practicascompose.ui.theme.PracticasComposeTheme

@Composable
fun ComposeStructure (topAppBar : @Composable () ->Unit, contentApp : @Composable (PaddingValues) ->Unit, statusBar: Boolean){
    val statusBarHeight = with(LocalDensity.current) { WindowInsets.statusBars.getTop(LocalDensity.current).toDp() }
    val topPad =if (!statusBar) statusBarHeight else 0.dp
    PracticasComposeTheme {
        Surface (color = MaterialTheme.colorScheme.background) {
            Scaffold (
                modifier = Modifier.padding(top=topPad),
                topBar = {  topAppBar() },
                content = { Column (modifier = Modifier
                    .fillMaxSize()
                    .padding(it)){

                contentApp(it)} }
            )

        }
    }
}