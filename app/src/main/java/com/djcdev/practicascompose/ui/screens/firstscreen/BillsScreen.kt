package com.djcdev.practicascompose.ui.screens.firstscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.djcdev.practicascompose.ui.companioncomposables.ComposeStructure
import com.djcdev.practicascompose.ui.companioncomposables.TopBarAction
import com.djcdev.practicascompose.ui.navigation.FilterBills
import com.djcdev.practicascompose.ui.navigation.Home
import com.djcdev.practicascompose.ui.screens.firstscreen.components.BillsList


@Composable
fun BillsScreen(navController: NavController) {

    val billsViewModel :BillsViewModel = hiltViewModel()


    val isLoading by billsViewModel.isBillsLoading.collectAsState()
    val error by billsViewModel.errorInNet.collectAsState()
    val mock by billsViewModel.isMockCheked.collectAsState()
    val bills by billsViewModel.bills.collectAsState()

    billsViewModel.getFacturas()

    ComposeStructure(
        topAppBar = {
            TopBarAction(name = "Facturas",
                toBackButton = { navController.navigate(Home) },
                toActionButton = { navController.navigate(FilterBills) })
        }, statusBar = true,
        contentApp = {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Mock", color = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.weight(1f))
                    Switch(
                        checked = mock,
                        onCheckedChange = {
                            billsViewModel.setIsMockCheked(!mock)
                            billsViewModel.getFacturas()
                        }
                    )
                }
                if (isLoading) {

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(35.dp)
                                .background(Color.Transparent),
                            color = Color.Green
                        )
                    }

                } else {
                    if (!error) {

                        BillsList(list = bills)

                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Ha ocurrido un problema al cargar")
                        }
                    }
                }
            }
        })




}


@Preview
@Composable
fun PreviewBills() {
    BillsScreen(navController = NavController(LocalContext.current))
}