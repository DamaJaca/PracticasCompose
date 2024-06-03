package com.djcdev.practicascompose.ui.screens.secondscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.djcdev.practicascompose.R
import com.djcdev.practicascompose.ui.companioncomposables.ComposeStructure
import com.djcdev.practicascompose.ui.companioncomposables.TopBarNoAction
import com.djcdev.practicascompose.ui.navigation.Home
import com.djcdev.practicascompose.ui.screens.secondscreen.components.TabContent1
import com.djcdev.practicascompose.ui.screens.secondscreen.components.TabContent2
import com.djcdev.practicascompose.ui.screens.secondscreen.components.TabContent3

@Composable
fun SecondScreen(navController: NavController) {
    ComposeStructure(topAppBar = {
        TopBarNoAction(name = stringResource(id = R.string.smart_solar)) {
            navController.navigate(
                Home
            )
        }
    },
        statusBar = true,
        contentApp = {
            val tabs = listOf("Mi instalación", "Energía", "Detalles")
            var selectedTab by rememberSaveable {
                mutableStateOf(0)
            }

            Column {
                TabRow(selectedTabIndex = selectedTab) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = { Text(text = title) }
                        )
                    }
                }
            }
            Box(modifier = Modifier.padding(16.dp)){
                when (selectedTab) {
                    0-> TabContent1()
                    1-> TabContent2()
                    2-> TabContent3()
                }
            }


        })
}

@Preview
@Composable
fun PreviewSecondScreen() {
    SecondScreen(navController = NavController(LocalContext.current))
}