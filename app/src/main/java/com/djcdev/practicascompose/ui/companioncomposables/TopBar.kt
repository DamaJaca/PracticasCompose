package com.djcdev.practicascompose.ui.companioncomposables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.djcdev.practicascompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarNoAction(name: String, toBackButton: () -> Unit) {
    Column {
        TopAppBar(
            title = { Text(text = "") },
            navigationIcon = @Composable {
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { toBackButton() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = stringResource(id = R.string.back_to_menu),
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                }
            }

        )
        Text(
            text = name, style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            ),
            modifier = Modifier.padding(8.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarAction(name: String, toBackButton: () -> Unit, toActionButton: () -> Unit) {
    Column {
        TopAppBar(
            title = { Text(text = "") },
            navigationIcon = @Composable {
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { toBackButton() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = stringResource(id = R.string.back_to_menu),
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                }
            },
            actions = @Composable {
                Icon(
                    painter = painterResource(id = R.drawable.filtericon_3x),
                    contentDescription = null, tint = Color.Black,
                    modifier = Modifier.clickable { toActionButton() }
                )
            }
        )
        Text(
            text = name, style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            ),
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview
@Composable
fun PreviewTopBar() {
    TopBarAction(name = "TopBar", toBackButton = {}, toActionButton = {})
}