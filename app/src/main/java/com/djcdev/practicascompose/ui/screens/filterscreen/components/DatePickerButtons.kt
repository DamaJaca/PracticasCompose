package com.djcdev.practicascompose.ui.screens.filterscreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.djcdev.practicascompose.ui.screens.firstscreen.BillsViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerButtons(viewModel: BillsViewModel = hiltViewModel()){

    val showInitialDialog by viewModel.showInitialDatePiclerDialog.collectAsState()
    val showFinalDialog by viewModel.showFinalDatePickerDialog.collectAsState()
    val initialDate by viewModel.initialDate.collectAsState()
    val finalDate by viewModel.finalDate.collectAsState()


    val datePicker = rememberDatePickerState(initialSelectedDateMillis = System.currentTimeMillis())

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
        DatePickerDialog(
            onDismissRequest =  { viewModel.setShowInitialDatePickerDialog(false) },
            confirmButton = {
                TextButton(onClick = {
                    val selectedMillis = datePicker.selectedDateMillis
                    if (selectedMillis != null) {
                        val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        val formattedDate = dateFormatter.format(Date(selectedMillis))
                        viewModel.setInitialDate(formattedDate)
                        viewModel.setShowInitialDatePickerDialog(false)
                    }
                }){
                    Text(text = "Aceptar", color = MaterialTheme.colorScheme.primary)
                }

            },
            dismissButton = {
                TextButton(onClick = { viewModel.setShowInitialDatePickerDialog(false) }) {
                    Text("Cancel")
                }
            }
        ){
            DatePicker(state= datePicker)
        }
    }

    if (showFinalDialog) {
        DatePickerDialog(
            onDismissRequest =  { viewModel.setShowFinalDatePickerDialog(false) },
            confirmButton = {
                TextButton(onClick = {
                    val selectedMillis = datePicker.selectedDateMillis
                    if (selectedMillis != null) {
                        val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        val formattedDate = dateFormatter.format(Date(selectedMillis))
                        viewModel.setFinalDate(formattedDate)
                        viewModel.setShowFinalDatePickerDialog(false)
                    }
                }){
                    Text(text = "Aceptar", color = MaterialTheme.colorScheme.primary)
                }

            },
            dismissButton = {
                TextButton(onClick = { viewModel.setShowFinalDatePickerDialog(false) }) {
                    Text("Cancel")
                }
            }
        ){
            DatePicker(state= datePicker)
        }
    }
}