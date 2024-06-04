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
import com.djcdev.practicascompose.ui.screens.login.components.BottomContentLogin
import com.djcdev.practicascompose.ui.screens.login.components.MediumContentLogin
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





@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    LoginScreen(navController = rememberNavController())
}
