package com.djcdev.practicascompose.ui.screens.filterscreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.djcdev.practicascompose.ui.screens.firstscreen.BillsViewModel

@Composable
fun DatePickerButtons(){
    val viewModel: BillsViewModel = hiltViewModel()
    val showInitialDialog by viewModel.showInitialDatePiclerDialog.collectAsState()
    val showFinalDialog by viewModel.showFinalDatePickerDialog.collectAsState()
    val initialDate by viewModel.initialDate.collectAsState()
    val finalDate by viewModel.finalDate.collectAsState()

    Row(
        modifier= Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
        ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "Fecha de Inicio")
            Button(
                onClick = { viewModel.setShowInitialDatePickerDialog(true) },
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .padding(bottom = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
            ) {
                Text(text = initialDate,
                    color = MaterialTheme.colorScheme.primary)
            }
        }
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "Fecha de fin")
            Button(
                onClick = { viewModel.setShowFinalDatePickerDialog(true) },
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .padding(bottom = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
            ) {
                Text(text = finalDate,
                    color = MaterialTheme.colorScheme.primary)
            }
        }
    }

    if (showInitialDialog) {
        Dialog(onDismissRequest = { viewModel.setShowInitialDatePickerDialog(false) }) {
            Surface {
                DatePickerDialogContent(
                    onDateSelected = { date ->
                        viewModel.setInitialDate(date)
                        viewModel.setShowInitialDatePickerDialog(false)
                    },
                    onDismiss = { viewModel.setShowInitialDatePickerDialog(false) }
                )
            }
        }
    }

    if (showFinalDialog) {
        Dialog(onDismissRequest = { viewModel.setShowFinalDatePickerDialog(false) }) {
            Surface {
                DatePickerDialogContent(
                    onDateSelected = { date ->
                        viewModel.setFinalDate(date)
                        viewModel.setShowFinalDatePickerDialog(false)
                    },
                    onDismiss = { viewModel.setShowFinalDatePickerDialog(false) }
                )
            }
        }
    }
}