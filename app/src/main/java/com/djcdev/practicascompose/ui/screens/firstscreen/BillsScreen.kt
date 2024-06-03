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
import androidx.navigation.NavController
import com.djcdev.practicascompose.ui.companioncomposables.ComposeStructure
import com.djcdev.practicascompose.ui.companioncomposables.TopBarAction
import com.djcdev.practicascompose.ui.navigation.FilterBills
import com.djcdev.practicascompose.ui.navigation.Home
import com.djcdev.practicascompose.ui.screens.firstscreen.components.BillsList


@Composable
fun BillsScreen(navController: NavController, viewModel :BillsViewModel = hiltViewModel()) {


    val isLoading by viewModel.isBillsLoading.collectAsState()
    val error by viewModel.errorInNet.collectAsState()
    val mock by viewModel.isMockCheked.collectAsState()
    val bills by viewModel.bills.collectAsState()
    val isFiltered by viewModel.isFiltered.collectAsState()

    if(!isFiltered){
        viewModel.getFacturas()
    }

    ComposeStructure(
        topAppBar = {
            TopBarAction(name = "Facturas",
                toBackButton = { navController.navigate(Home) },
                toActionButton = { if (!isLoading)navController.navigate(FilterBills) })
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
                            viewModel.setIsMockCheked(!mock)
                            if (!isFiltered){
                                viewModel.getFacturas()
                            }else{
                                viewModel.filterFacturas()
                            }
                            viewModel.getMaxImport()
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
                    if (bills.isNotEmpty()) {

                        BillsList(list = bills, modifier= Modifier.padding(horizontal = 16.dp))

                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            val msg = if (error)"Ha ocurrido un problema al cargar" else "No se han encontrado facturas con esas especificaciones"
                            Text(text = msg, modifier = Modifier.padding(horizontal = 16.dp))
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