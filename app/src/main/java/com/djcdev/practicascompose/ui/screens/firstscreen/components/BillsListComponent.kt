package com.djcdev.practicascompose.ui.screens.firstscreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.djcdev.practicas.domain.model.FacturaModel
import com.djcdev.practicascompose.R


@Composable
fun BillsList(){
    LazyColumn {

    }
}

@Composable
fun ItemBill(factura:FacturaModel){
    var showDialog by remember {
        mutableStateOf(false)
    }
    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
    ){
        Row(modifier = Modifier
            .clickable {
                showDialog = true
            }
            .fillMaxWidth()
            .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            if (showDialog) AlertDialogBills { showDialog=false }

            Column {

                Text(text = "${factura.importe} €", style = MaterialTheme.typography.titleMedium)
                if (factura.estado=="Pendiente de pago"){
                    Text(text = "Pendiente de pago", color = Color.Red, style = MaterialTheme.typography.bodyMedium)
                }

            }

            Icon(painter = painterResource(id = R.drawable.ic_next), contentDescription = null, tint = Color.Black)

        }

        Divider()

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewItemBill(){
    ItemBill(factura = FacturaModel("Pendi", 20.85, "20/10/1994"))
}