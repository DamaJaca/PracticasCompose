package com.djcdev.practicascompose.ui.companioncomposables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.djcdev.practicascompose.R

@Composable
fun HeadderIconIberdrola() {
    Image(
        painter = painterResource(id = R.drawable.ic_logo_iberdrola),
        contentDescription = "Logo de iberdrola",
        modifier = Modifier
            .width(200.dp)
            .height(80.dp)
            .padding(vertical = 16.dp)
    )
}