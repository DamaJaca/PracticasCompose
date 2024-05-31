package com.djcdev.practicascompose.ui.screens.filterscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.djcdev.practicascompose.ui.companioncomposables.ComposeStructure
import com.djcdev.practicascompose.ui.companioncomposables.TopBarNoAction
import com.djcdev.practicascompose.ui.navigation.FilterBills
import com.djcdev.practicascompose.ui.navigation.Home
import com.djcdev.practicascompose.ui.navigation.PracticeOne
import com.djcdev.practicascompose.ui.screens.filterscreen.components.ButtomsFilter
import com.djcdev.practicascompose.ui.screens.filterscreen.components.DatePickerButtons
import com.djcdev.practicascompose.ui.screens.filterscreen.components.DatePickerDialogContent
import com.djcdev.practicascompose.ui.screens.firstscreen.BillsViewModel

@Composable
fun FilterBills (navController: NavController) {

    ComposeStructure(topAppBar = { TopBarNoAction(name = "Filtro de Facturas") { navController.navigate(PracticeOne) } }, statusBar = true,
        contentApp = {
            Column (modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)

            ){
                ContentFilter()

                ButtomsFilter(navController)
            }

        }
    )

}



@Composable
fun ContentFilter() {

    DatePickerButtons()

}

@Preview(showBackground = true)
@Composable
fun PreviewFilterBills(){
    FilterBills(navController = NavController(LocalContext.current))
}
