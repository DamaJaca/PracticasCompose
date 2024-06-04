package com.djcdev.practicascompose.ui.screens.signup.components

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.djcdev.practicascompose.domain.model.exceptions.FailedSignUp
import com.djcdev.practicascompose.ui.companioncomposables.loginregister.RelativeLayoutComponent
import com.djcdev.practicascompose.ui.companioncomposables.loginregister.ShowErrorRegisterMessage
import com.djcdev.practicascompose.ui.navigation.Home
import com.djcdev.practicascompose.ui.navigation.Login
import com.djcdev.practicascompose.ui.screens.signup.SignUpViewModel

@Composable
fun BottomSignUp(navController: NavController, modifier: Modifier = Modifier) {

    val viewModel: SignUpViewModel = hiltViewModel()

    val context = LocalContext.current

    val isLoading by viewModel.isLoading.collectAsState()

    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val confirmPass by viewModel.confirmPassword.collectAsState()

    var showDialog by rememberSaveable {
        mutableStateOf(false)
    }
    var failedLog: FailedSignUp? by remember {
        mutableStateOf(null)
    }
    var signedUp by rememberSaveable {
        mutableStateOf(false)
    }

    fun setShowDialog(boolean: Boolean) {
        showDialog = boolean
    }

    Column (
        modifier = modifier
    ) {
        if (signedUp) Toast.makeText(
            context,
            "Se ha realizado el registro con exito",
            Toast.LENGTH_SHORT
        ).show()
        Button(
            onClick = {
                viewModel.changeIsLoading(true)
                if(password==confirmPass && password!=""){
                    viewModel.singUp(email, password) { success: Boolean, fail: FailedSignUp? ->
                        if (fail == null) {
                            if (success) {
                                signedUp = true

                            }
                            navController.navigate(Home)
                        } else {
                            failedLog = fail
                            showDialog = true
                        }
                    }
                }else{
                    failedLog= FailedSignUp.NotSamePass
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
                        .background(Color.Transparent),
                    color = Color.White
                )
            } else {
                Text("Registrarse")
            }
        }

        if (showDialog) {
            ShowErrorRegisterMessage(failedLog) {
                viewModel.changeIsLoading(false)
                setShowDialog(false)
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