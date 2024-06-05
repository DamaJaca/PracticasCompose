package com.djcdev.practicascompose.ui.screens.remember.components

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.djcdev.practicascompose.R
import com.djcdev.practicascompose.domain.model.exceptions.FailedLogin
import com.djcdev.practicascompose.ui.companioncomposables.loginregister.RelativeLayoutComponent
import com.djcdev.practicascompose.ui.navigation.Login
import com.djcdev.practicascompose.ui.screens.remember.RememberViewModel

@Composable
fun BottomRemember(navController: NavController, modifier: Modifier = Modifier) {

    val viewModel: RememberViewModel = hiltViewModel()

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
            stringResource(id = R.string.send_recuperation_email),
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
                            navController.navigate(Login) {
                                popUpTo(Login) { inclusive = true }
                            }
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
                        .background(Color.Transparent)
                )
            } else {
                Text(stringResource(id = R.string.recuperation_email))
            }
        }


        RelativeLayoutComponent()

        OutlinedButton(
            onClick = {
                navController.navigate(Login) {
                    popUpTo(Login) { inclusive = true }
                }
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
                text = stringResource(id = R.string.back_to_login),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}