package com.djcdev.practicascompose.ui.screens.firstscreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.djcdev.practicascompose.domain.model.FacturaModel
import com.djcdev.practicascompose.R


@Composable
fun BillsList(list : List<FacturaModel>, modifier: Modifier=Modifier){
    LazyColumn (modifier = modifier) {
        items(list){
            ItemBill(factura = it)
        }
    }
}

@Composable
fun ItemBill(factura: FacturaModel){
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
            .padding(vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            if (showDialog) AlertDialogBills { showDialog=false }

            Column {

                Text(text = factura.fecha, style = MaterialTheme.typography.titleMedium)
                if (factura.estado== stringResource(id = R.string.pending)){
                    Text(text = stringResource(id = R.string.pending), color = Color.Red, style = MaterialTheme.typography.bodyMedium)
                }

            }

            Row {
                Text(text = "${factura.importe} €")
                Spacer(modifier = Modifier.padding(8.dp))
                Icon(painter = painterResource(id = R.drawable.ic_next), contentDescription = null, tint = Color.Black)
            }


        }

        HorizontalDivider()

    }
}
val facturas = listOf(
    FacturaModel("Pagada", 123.45, "01/01/2021"),
    FacturaModel("Pendiente de pago", 234.56, "15/02/2021"),
    FacturaModel("Pagada", 345.67, "23/03/2021"),
    FacturaModel("Pendiente de pago", 456.78, "04/04/2021"),
    FacturaModel("Pagada", 567.89, "12/05/2021"),
    FacturaModel("Pendiente de pago", 678.90, "21/06/2021"),
    FacturaModel("Pagada", 789.01, "30/07/2021"),
    FacturaModel("Pendiente de pago", 890.12, "08/08/2021"),
    FacturaModel("Pagada", 901.23, "17/09/2021"),
    FacturaModel("Pendiente de pago", 112.34, "26/10/2021"),
    FacturaModel("Pagada", 223.45, "05/11/2021"),
    FacturaModel("Pendiente de pago", 334.56, "14/12/2021"),
    FacturaModel("Pagada", 445.67, "22/01/2022"),
    FacturaModel("Pendiente de pago", 556.78, "01/02/2022"),
    FacturaModel("Pagada", 667.89, "10/03/2022"),
    FacturaModel("Pendiente de pago", 778.90, "19/04/2022"),
    FacturaModel("Pagada", 889.01, "28/05/2022"),
    FacturaModel("Pendiente de pago", 990.12, "06/06/2022"),
    FacturaModel("Pagada", 101.23, "15/07/2022"),
    FacturaModel("Pendiente de pago", 212.34, "24/08/2022")
)


@Preview(showBackground = true)
@Composable
fun PreviewList(){
    BillsList(list = facturas)
}

@Preview(showBackground = true)
@Composable
fun PreviewItemBill(){
    ItemBill(factura = FacturaModel("Pendi", 20.85, "20/10/1994"))
}