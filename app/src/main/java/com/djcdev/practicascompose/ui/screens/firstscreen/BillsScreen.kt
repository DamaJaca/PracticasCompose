package com.djcdev.practicascompose.ui.screens.firstscreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.djcdev.practicascompose.ui.companioncomposables.ComposeStructure
import com.djcdev.practicascompose.ui.companioncomposables.TopBarAction
import com.djcdev.practicascompose.ui.navigation.FilterBills
import com.djcdev.practicascompose.ui.navigation.Home


@Composable
fun BillsScreen(navController: NavController) {
    ComposeStructure(
        topAppBar = {
            TopBarAction(name = "Facturas",
                toBackButton = { navController.navigate(Home) },
                toActionButton = { navController.navigate(FilterBills) })
        })
    {

    }
}


@Preview
@Composable
fun PreviewBills() {
    BillsScreen(navController = NavController(LocalContext.current))
}