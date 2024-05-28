package com.djcdev.practicascompose.ui.companioncomposables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.djcdev.practicascompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarNoAction(name:String, toBackButton: ()->Unit) {
    TopAppBar(
        title = { Text(text = name)},
        navigationIcon = @Composable {
            Icon(
                imageVector =  Icons.Default.KeyboardArrowLeft,
                contentDescription = null,
                modifier = Modifier.clickable { toBackButton() }
            )
        }
    )
}