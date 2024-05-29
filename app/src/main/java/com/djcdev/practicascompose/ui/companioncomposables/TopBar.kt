package com.djcdev.practicascompose.ui.companioncomposables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.djcdev.practicascompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarNoAction(name:String, toBackButton: ()->Unit?) {
    TopAppBar(
        title = { Text(text = name)},
        navigationIcon = @Composable {
            Row (verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { toBackButton() }) {
                Icon(
                    imageVector =  Icons.Default.KeyboardArrowLeft,
                    contentDescription = null,
                    tint=MaterialTheme.colorScheme.primary
                )
                Text(text = "Volver al menú", fontSize= 8.sp, color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.padding(8.dp))
            }
        }
    )
}

@Preview
@Composable
fun PreviewTopBar(){
    TopBarNoAction(name = "TopBar") {
        
    }
}