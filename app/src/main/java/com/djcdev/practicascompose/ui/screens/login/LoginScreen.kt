package com.djcdev.practicascompose.ui.screens.login

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
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
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.djcdev.practicascompose.R
import com.djcdev.practicascompose.domain.model.UserModel
import com.djcdev.practicascompose.domain.model.exceptions.FailedLogin
import com.djcdev.practicascompose.ui.companioncomposables.ComposeStructure
import com.djcdev.practicascompose.ui.companioncomposables.HeadderIconIberdrola
import com.djcdev.practicascompose.ui.companioncomposables.loginregister.RelativeLayoutComponent
import com.djcdev.practicascompose.ui.companioncomposables.loginregister.ShowErrorMessage
import com.djcdev.practicascompose.ui.navigation.Home
import com.djcdev.practicascompose.ui.navigation.Remember
import com.djcdev.practicascompose.ui.navigation.Signup
import com.djcdev.practicascompose.ui.theme.GreenDark
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    var userModel by remember {
        mutableStateOf<UserModel?>(null)
    }
    var isUserLoaded by rememberSaveable {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        val user = getUser(context).first()
            userModel = user
            isUserLoaded = true


    }

    ComposeStructure(topAppBar = { }, statusBar = false,
        contentApp = {
            Box(modifier = Modifier.fillMaxSize()) {
                if (!isUserLoaded) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(24.dp)
                            .background(Color.Transparent)
                            .align(Alignment.Center)
                    )
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        if (userModel!!.user == null && userModel!!.pass == null) {
                            runBlocking {
                                saveUser("", "", context)
                                userModel = getUser(context).first()
                            }
                        }


                        if (userModel!!.user == "" && userModel!!.pass == "") {
                            HeadderIconIberdrola()

                            MediumContentLogin(navController)

                            BottomContentLogin(navController)
                        } else {
                            LaunchedEffect(Unit) {
                                navController.navigate(Home)
                            }
                        }

                    }


                }
            }
        })


}

suspend fun saveUser(user: String, pass: String, context: Context) {
    context.dataStore.edit { preferences ->
        preferences[stringPreferencesKey("user")] = user
        preferences[stringPreferencesKey("pass")] = pass

    }
}

private fun getUser(context: Context): Flow<UserModel> {
    return context.dataStore.data.map {
        UserModel(
            it[stringPreferencesKey("user")],
            it[stringPreferencesKey("pass")]
        )
    }
}

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




@Composable
fun MediumContentLogin(navController: NavController) {

    val viewModel: LoginViewModel = hiltViewModel()

    val username by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val rememberPassword by viewModel.rememberPassword.collectAsState()
    val passwordVisible by viewModel.passwordVisible.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { viewModel.setEmail(it) },
            label = { Text("Usuario") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { /* Move focus to next field */ })
        )
        Spacer(modifier = Modifier.padding(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { viewModel.setPassword(it) },
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

        TextButton(
            onClick = {
                navController.navigate(Remember)
            },
            modifier = Modifier
                .padding(top = 6.dp)
                .align(Alignment.Start),
            colors = ButtonDefaults.textButtonColors(
                contentColor = GreenDark
            )
        ) {
            Text("He olvidado mi contraseña")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = rememberPassword,
                onCheckedChange = { viewModel.changeCheckPass() },
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    checkmarkColor = Color.White
                )
            )
            Text("Recordar Usuario y contraseña")
        }

    }
}




@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    LoginScreen(navController = rememberNavController())
}
