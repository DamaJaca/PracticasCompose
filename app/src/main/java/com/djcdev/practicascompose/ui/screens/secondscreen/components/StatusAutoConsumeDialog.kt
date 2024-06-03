package com.djcdev.practicascompose.ui.screens.secondscreen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.djcdev.practicascompose.R

@Composable
fun StatusAutoConsumeDialog(onDimiss : () ->Unit){
    AlertDialog(
        onDismissRequest = { onDimiss() },
        title = {
            Text(text = stringResource(id = R.string.estado_solucitud2),
                textAlign = TextAlign.Center,
                softWrap = true)
        },
        text = {
            Text(
                text = stringResource(id = R.string.stimated_time_smart_solar),
                textAlign = TextAlign.Center
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    onDimiss()
                },
                modifier = Modifier.fillMaxWidth()) {
                Text(stringResource(id = R.string.ok))
            }
        }
    )
}

@Preview
@Composable
fun PreviweDialogStatusConsume(){
    StatusAutoConsumeDialog {
        //
    }
}