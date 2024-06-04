package com.djcdev.practicascompose.ui.screens.login.components

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
import com.djcdev.practicascompose.domain.model.exceptions.FailedLogin
import com.djcdev.practicascompose.ui.companioncomposables.loginregister.RelativeLayoutComponent
import com.djcdev.practicascompose.ui.companioncomposables.loginregister.ShowErrorMessage
import com.djcdev.practicascompose.ui.navigation.Home
import com.djcdev.practicascompose.ui.navigation.Signup
import com.djcdev.practicascompose.ui.screens.login.LoginViewModel
import com.djcdev.practicascompose.ui.screens.login.saveUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun BottomContentLogin(navController: NavController, modifier: Modifier = Modifier) {

    val viewModel: LoginViewModel = hiltViewModel()

    val context = LocalContext.current

    val isLoading by viewModel.isLoading.collectAsState()
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val rememberPassword by viewModel.rememberPassword.collectAsState()
    var showDialog by rememberSaveable {
        mutableStateOf(false)
    }
    var failedLog: FailedLogin? by remember {
        mutableStateOf(null)
    }
    var logged by rememberSaveable {
        mutableStateOf(false)
    }

    fun setShowDialog(boolean: Boolean) {
        showDialog = boolean
    }

    Column(
        modifier = modifier
    ) {

        if (logged) Toast.makeText(
            LocalContext.current,
            "Se ha iniciado sesion",
            Toast.LENGTH_SHORT
        ).show()
        Button(

            onClick = {
                viewModel.changeIsLoading(true)
                if(email!="" && password!= ""){
                    viewModel.login(email, password) { success: Boolean, fail: FailedLogin? ->
                        if (fail == null) {
                            if (success) {
                                logged = true
                                if (rememberPassword) {
                                    CoroutineScope(Dispatchers.IO).launch {
                                        saveUser(email, password, context)
                                    }
                                } else {
                                    CoroutineScope(Dispatchers.IO).launch {
                                        saveUser("", "", context)
                                    }
                                }

                            }
                            navController.navigate(Home)
                        } else {
                            failedLog = fail
                            showDialog = true
                        }
                    }
                }else{
                    failedLog= FailedLogin.MissingSomething
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
                Text("Acceder")
            }
        }

        if (showDialog) {
            ShowErrorMessage(failedLog) {
                viewModel.changeIsLoading(false)
                setShowDialog(false)
            }
        }


        RelativeLayoutComponent()

        OutlinedButton(
            onClick = {
                navController.navigate(Signup)
            },
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Registrarse",
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}