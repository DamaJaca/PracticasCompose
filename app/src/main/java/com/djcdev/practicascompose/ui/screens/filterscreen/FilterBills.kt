package com.djcdev.practicascompose.ui.screens.filterscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.djcdev.practicascompose.R
import com.djcdev.practicascompose.ui.companioncomposables.ComposeStructure
import com.djcdev.practicascompose.ui.companioncomposables.TopBarNoAction
import com.djcdev.practicascompose.ui.screens.filterscreen.components.ButtomsFilter
import com.djcdev.practicascompose.ui.screens.filterscreen.components.CheckedComponents
import com.djcdev.practicascompose.ui.screens.filterscreen.components.DatePickerButtons
import com.djcdev.practicascompose.ui.screens.filterscreen.components.RangeSliderComponent
import com.djcdev.practicascompose.ui.screens.firstscreen.BillsViewModel

@Composable
fun FilterBills (navController: NavController, viewModel: BillsViewModel) {

    ComposeStructure(topAppBar = { TopBarNoAction(name = stringResource(id = R.string.bills_filter), toBackButton = { navController.navigateUp() })  }, statusBar = true,
        contentApp = {
            LazyColumn (modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)

            ){
                item{
                    ContentFilter(viewModel)
                }

                item{
                    ButtomsFilter(navController = navController, viewModel =  viewModel)
                }
            }

        }
    )

}



@Composable
fun ContentFilter(viewModel: BillsViewModel) {
    Column {

        DatePickerButtons(viewModel)

        Spacer(modifier = Modifier.padding(8.dp))
        HorizontalDivider(Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.padding(8.dp))

        RangeSliderComponent(viewModel)

        Spacer(modifier = Modifier.padding(8.dp))
        HorizontalDivider(Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.padding(8.dp))

        CheckedComponents(viewModel)

        Spacer(modifier = Modifier.padding(8.dp))
        HorizontalDivider(Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.padding(8.dp))


    }

}
