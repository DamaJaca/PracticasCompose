package com.djcdev.practicascompose.ui.screens.signup

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.djcdev.practicascompose.R
import com.djcdev.practicascompose.domain.model.exceptions.FailedSignUp
import com.djcdev.practicascompose.ui.companioncomposables.ComposeStructure
import com.djcdev.practicascompose.ui.companioncomposables.HeadderIconIberdrola
import com.djcdev.practicascompose.ui.navigation.Home
import com.djcdev.practicascompose.ui.navigation.Login
import com.djcdev.practicascompose.ui.screens.login.RelativeLayoutComponent


@Composable
fun SignUpScreen(navController: NavController){
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

                ContentSignUp(navController)

                BottomSignUp(navController)
            }
        }

    })
}

@Composable
fun ContentSignUp(navController: NavController) {
    val viewModel: SignUpViewModel = hiltViewModel()


    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val passwordVisible by viewModel.passwordVisible.collectAsState()
    val confirmPassVisible by viewModel.confirmPasswordVisible.collectAsState()
    val confirmPass by viewModel.confirmPassword.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = email,
            onValueChange = { viewModel.setEmail(it) },
            label = { Text("Usuario") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )
        Spacer(modifier = Modifier.padding(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {viewModel.setPassword(it)},
            label = { Text("Contraseña") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { /* Handle Done action */ }),
            trailingIcon = {
                IconButton(onClick = { viewModel.changeVisibilityPass() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_eye),
                        contentDescription = "Contrasña"
                    )
                }
            }
        )
        Spacer(modifier = Modifier.padding(8.dp))

        OutlinedTextField(
            value = confirmPass,
            onValueChange = { viewModel.setConfirmPassword(it) },
            label = { Text("Repita la contraseña") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (confirmPassVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { /* Handle Done action */ }),
            trailingIcon = {
                IconButton(onClick = { viewModel.changeConfirmVisibilityPass() }) {
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
            LocalContext.current,
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
                    failedLog=FailedSignUp.NotSamePass
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

@Composable
fun ShowErrorRegisterMessage(fail: FailedSignUp?, onDimiss: () -> Unit) {

    AlertDialog(
        onDismissRequest = { onDimiss() },
        title = {
            Text(text = "Error!")
        },
        text = {
            Text(
                text = when (fail) {
                    FailedSignUp.InvalidCredential -> "El email introducido no es válido. Comprueba que es un email válido"
                    FailedSignUp.UserAlreadyExist -> "El usuario que intenta introducir ya está registrado"
                    FailedSignUp.WeakPas -> "La contraseña es demasiado debil"
                    FailedSignUp.NotSamePass -> "Las contraseñas no coinciden o los campos estan vacios"
                    null -> "Ha ocurrido un error inesperado al intentar realizar esa acción"
                }
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    onDimiss()
                }) {
                Text("OK")
            }
        }
    )

}

@Preview(showBackground = true)
@Composable
fun PreviewSignUpScreen (){
    SignUpScreen(navController = rememberNavController())
}
