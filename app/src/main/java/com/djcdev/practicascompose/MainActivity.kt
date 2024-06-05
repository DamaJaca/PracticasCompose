package com.djcdev.practicascompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.djcdev.practicascompose.ui.navigation.NavApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object{
        const val URL_DESTINO = "https://www.iberdrola.com"
        const val DEFAULT_DATE= "dd/mm/yyyy"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                NavApp()
        }

    }
}
