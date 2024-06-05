package com.djcdev.practicascompose.ui.screens.firstscreen.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.djcdev.practicascompose.R

@Composable
fun AlertDialogBills(onDimiss: () ->Unit){
    AlertDialog(
        onDismissRequest = { onDimiss() },
        title = {
            Text(text = stringResource(id = R.string.information_factura_title))
        },
        text = {
            Text(stringResource(id = R.string.not_enabled_function))
        },
        confirmButton = {
            Button(
                onClick = {
                    onDimiss()
                }) {
                Text(stringResource(id = R.string.close))
            }
        }
    )
}