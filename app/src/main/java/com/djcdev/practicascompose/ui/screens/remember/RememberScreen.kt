package com.djcdev.practicascompose.ui.screens.remember

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.djcdev.practicascompose.domain.model.exceptions.FailedLogin
import com.djcdev.practicascompose.ui.companioncomposables.ComposeStructure
import com.djcdev.practicascompose.ui.companioncomposables.HeadderIconIberdrola
import com.djcdev.practicascompose.ui.companioncomposables.loginregister.RelativeLayoutComponent
import com.djcdev.practicascompose.ui.navigation.Login

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

                ContentRemember(navController)

                BottomRemember(navController)
            }
        }

    })
}

@Composable
fun ContentRemember(navController: NavController) {
    var username by remember { mutableStateOf("") }

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Email al que enviar correo de recuperacion") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { /* Move focus to next field */ })
        )


}

@Composable
fun BottomRemember(navController: NavController, modifier: Modifier = Modifier) {

    val viewModel: RememberViewModel = hiltViewModel()

    val context = LocalContext.current

    val isLoading by viewModel.isLoading.collectAsState()
    val email by viewModel.email.collectAsState()
    var showDialog by rememberSaveable {
        mutableStateOf(false)
    }
    var failedLog: FailedLogin? by remember {
        mutableStateOf(null)
    }
    var logged by rememberSaveable {
        mutableStateOf(false)
    }


    Column (
        modifier = modifier
    ) {
        if (logged) Toast.makeText(
            LocalContext.current,
            "Se ha enviado el correo de recuperacion",
            Toast.LENGTH_SHORT
        ).show()
        Button(
            onClick = {
                viewModel.changeIsLoading(true)
                if(email!=""){
                    viewModel.remember(email) { success: Boolean, fail: FailedLogin? ->
                        if (fail == null) {
                            if (success) {
                                logged = true
                            }
                            navController.navigate(Login)
                        } else {
                            failedLog = fail
                            showDialog = true
                        }
                    }
                }else{
                    failedLog=FailedLogin.MissingSomething
                    showDialog=true
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color.Transparent)
                )
            } else {
                Text("Enviar Correo")
            }
        }


        RelativeLayoutComponent()

        OutlinedButton(
            onClick = {
                navController.navigate(Login)
            },
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            )
        ) {
            Text(
                text = "Volver a Inicio",
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRememberScreen (){
    RememberScreen(navController = rememberNavController())
}
