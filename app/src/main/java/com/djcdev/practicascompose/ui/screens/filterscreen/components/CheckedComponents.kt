package com.djcdev.practicascompose.ui.screens.filterscreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.djcdev.practicascompose.ui.screens.firstscreen.BillsViewModel

@Composable
fun CheckedComponents (viewModel :BillsViewModel = hiltViewModel()){



    val isPaid by viewModel.isPaidCheked.collectAsState()
    val isNulled by viewModel.isNulledChecked.collectAsState()
    val isFixed by viewModel.isFixedChecked.collectAsState()
    val isPending by viewModel.isPendingChecked.collectAsState()
    val isPlanned by viewModel.isPlannedChecked.collectAsState()

    Column(modifier = Modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        NewCheckBox("Pagada", isPaid){viewModel.changeIsPaid(it)}
        NewCheckBox("Anuladas", isNulled){viewModel.changeIsNulled(it)}
        NewCheckBox("Cuota Fija", isFixed){viewModel.changeIsFixed(it)}
        NewCheckBox("Pendiente de pago", isPending){viewModel.changeIsPending(it)}
        NewCheckBox("Plan de Pago", isPlanned){viewModel.changeIsPlanned(it)}
    }
}

@Composable
fun NewCheckBox(name:String, isChecked: Boolean, change :(Boolean)->Unit){
    Row(
        modifier = Modifier
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { change(it) }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = name)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChecks (){
    CheckedComponents()
}