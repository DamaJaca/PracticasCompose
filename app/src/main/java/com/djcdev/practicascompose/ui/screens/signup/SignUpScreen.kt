package com.djcdev.practicascompose.ui.screens.signup

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.djcdev.practicascompose.ui.companioncomposables.ComposeStructure
import com.djcdev.practicascompose.ui.companioncomposables.HeadderIconIberdrola
import com.djcdev.practicascompose.ui.screens.signup.components.BottomSignUp
import com.djcdev.practicascompose.ui.screens.signup.components.ContentSignUp


@Composable
fun SignUpScreen(navController: NavController){
            DisposableEffect(key1 = Unit) {
                onDispose { Log.d("Paco", "Disposed Sign Up") }
            }
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

                ContentSignUp()

                BottomSignUp(navController)
            }
        }

    })
}







@Preview(showBackground = true)
@Composable
fun PreviewSignUpScreen (){
    SignUpScreen(navController = rememberNavController())
}
