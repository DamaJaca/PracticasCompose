package com.djcdev.practicascompose.ui.screens.filterscreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.djcdev.practicascompose.ui.screens.firstscreen.BillsViewModel
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale


@Composable
fun RangeSliderComponent(viewModel: BillsViewModel) {
    val maxImport by viewModel.maxImport.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Facturas entre 0€ y ${doubleToFloat(maxImport)}€")

        Spacer(modifier = Modifier.height(16.dp))

        Slider(
            value = maxImport.toFloat(),
            onValueChange = { viewModel.setMaxImport(it.toDouble())},
            valueRange = 0f..viewModel.billsMaxImport.value
        )
    }
}

private fun doubleToFloat(importe: Double): Float {
    val symbols = DecimalFormatSymbols(Locale.getDefault()).apply {
        decimalSeparator = '.'
    }
    val formato = DecimalFormat("#.##", symbols)
    formato.isParseBigDecimal=true
    formato.maximumFractionDigits = 2
    return formato.format(importe).toFloat()

}

