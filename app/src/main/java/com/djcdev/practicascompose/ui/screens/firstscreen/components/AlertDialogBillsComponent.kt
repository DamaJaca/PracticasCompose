package com.djcdev.practicascompose.ui.screens.firstscreen.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.djcdev.practicascompose.domain.model.exceptions.FailedLogin

@Composable
fun AlertDialogBills(onDimiss: () ->Unit){
    AlertDialog(
        onDismissRequest = { onDimiss() },
        title = {
            Text(text = "Información")
        },
        text = {
            Text("Esta funcionalidad aún no está disponible")
        },
        confirmButton = {
            Button(
                onClick = {
                    onDimiss()
                }) {
                Text("Cerrar")
            }
        }
    )
}