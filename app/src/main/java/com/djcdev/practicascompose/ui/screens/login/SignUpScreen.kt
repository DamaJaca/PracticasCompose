package com.djcdev.practicascompose.ui.screens.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.djcdev.practicascompose.R
import com.djcdev.practicascompose.ui.companioncomposables.ComposeStructure
import com.djcdev.practicascompose.ui.companioncomposables.HeadderIconIberdrola
import com.djcdev.practicascompose.ui.navigation.Home
import com.djcdev.practicascompose.ui.navigation.Login
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


@Composable
fun SignUpScreen(navController: NavController){
    ComposeStructure(topAppBar = {  }) {

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HeadderIconIberdrola()

                ContentSignUp(navController)

                BottomSignUp(navController)
            }
        }

    }
}

@Composable
fun ContentSignUp(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var secondPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var secondPasswordVisible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nombre") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
        Spacer(modifier = Modifier.padding(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Usuario") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )
        Spacer(modifier = Modifier.padding(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { /* Handle Done action */ }),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_eye),
                        contentDescription = "Contrasña"
                    )
                }
            }
        )
        Spacer(modifier = Modifier.padding(8.dp))

        OutlinedTextField(
            value = secondPassword,
            onValueChange = { secondPassword = it },
            label = { Text("Repita la contraseña") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (secondPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { /* Handle Done action */ }),
            trailingIcon = {
                IconButton(onClick = { secondPasswordVisible = !secondPasswordVisible }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_eye),
                        contentDescription = "Contrasña"
                    )
                }
            }
        )

    }
}

@Composable
fun BottomSignUp(navController: NavController, modifier: Modifier= Modifier) {



    var isLoading by remember { mutableStateOf(false) }
    Column (
        modifier = modifier
    ) {
        Button(
            onClick = {
                //                        viewModel.login(username, password) { success: Boolean, fail: FailedLogin ->
                //                            isLoading = false
                //                            if (success) {
                //                                Toast.makeText(context, "Se ha iniciado sesion", Toast.LENGTH_SHORT)
                //                                    .show()
                //                                if (rememberPassword) {
                //                                    // Save username and password
                //                                }
                //                                navController.navigate("home")
                //                            } else {
                //                                // Handle login failure
                //                                Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
                //                            }
                //                        }
                runBlocking {

                    isLoading = true
                    delay(timeMillis = 2000L)
                }
                navController.navigate(Login)
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
                Text("Registrarse")
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
fun PreviewSignUpScreen (){
    SignUpScreen(navController = rememberNavController())
}
