package com.djcdev.practicascompose.ui.companioncomposables.loginregister

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RelativeLayoutComponent() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {

        Divider(color = Color.Gray)
        Text(
            text = "Tambien puedes",
            color = Color.Gray,
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 4.dp)
        )
    }
}