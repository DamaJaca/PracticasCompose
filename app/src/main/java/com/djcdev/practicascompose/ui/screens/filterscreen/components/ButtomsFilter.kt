package com.djcdev.practicascompose.ui.screens.filterscreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.djcdev.practicascompose.ui.navigation.PracticeOne
import com.djcdev.practicascompose.ui.screens.firstscreen.BillsViewModel

@Composable
fun ButtomsFilter(navController: NavController, modifier: Modifier = Modifier) {

    val viewModel: BillsViewModel = hiltViewModel()

    Column (
        modifier = modifier
    ) {
        Button(
            onClick = {
                viewModel.filterFacturas()
                navController.navigate(PracticeOne)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Text("Aplicar Filtros")

        }

        Spacer(modifier = Modifier.padding(16.dp))


        OutlinedButton(
            onClick = {
                viewModel.setFinalDate("dd/mm/yyyy")
                viewModel.setInitialDate("dd/mm/yyyy")
                viewModel.setMaxImport(0.00)
                viewModel.changeIsPaid(false)
                viewModel.changeIsPending(false)
                viewModel.changeIsNulled(false)
                viewModel.changeIsFixed(false)
                viewModel.changeIsPlanned(false)
            },
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            )
        ) {
            Text(
                text = "Eliminar Filtros",
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewButtoms(){
    ButtomsFilter(navController = NavController(LocalContext.current))
}