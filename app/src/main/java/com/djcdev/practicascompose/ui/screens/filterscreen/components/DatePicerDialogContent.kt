@file:OptIn(ExperimentalMaterial3Api::class)

package com.djcdev.practicascompose.ui.screens.filterscreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun DatePickerDialogContent(
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val datePicker =  rememberDatePickerState( initialSelectedDateMillis = System.currentTimeMillis())

    Column (
        modifier = Modifier.padding(16.dp)
    ){
        DatePicker (state =datePicker)
        Spacer(modifier = Modifier.padding(8.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
            TextButton(onClick = {
                val selectedMillis = datePicker.selectedDateMillis
                if (selectedMillis != null){
                    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    val formattedDate = dateFormatter.format(Date(selectedMillis))
                    onDateSelected(formattedDate)
                }
            }) {
                Text(text = "Aceptar")
            }
        }
    }
}