package com.djcdev.practicascompose.ui.screens.remember

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.djcdev.practicascompose.R
import com.djcdev.practicascompose.ui.companioncomposables.ComposeStructure
import com.djcdev.practicascompose.ui.companioncomposables.HeadderIconIberdrola
import com.djcdev.practicascompose.ui.screens.remember.components.BottomRemember

@Composable
fun RememberScreen(navController: NavController){
    ComposeStructure(topAppBar = {  }, statusBar = false,
        contentApp = {

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HeadderIconIberdrola()

                ContentRemember()

                BottomRemember(navController)
            }
        }

    })
}

@Composable
fun ContentRemember() {
    var username by remember { mutableStateOf("") }

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(stringResource(id = R.string.recuperation_email_hint)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        )


}



@Preview(showBackground = true)
@Composable
fun PreviewRememberScreen (){
    RememberScreen(navController = rememberNavController())
}
