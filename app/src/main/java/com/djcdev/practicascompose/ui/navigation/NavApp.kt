package com.djcdev.practicascompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.djcdev.practicascompose.ui.screens.home.HomeScreen
import com.djcdev.practicascompose.ui.screens.login.LoginScreen
import com.djcdev.practicascompose.ui.screens.remember.RememberScreen
import com.djcdev.practicascompose.ui.screens.signup.SignUpScreen


@Composable
fun NavApp () {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login){
        composable<Login>{
            LoginScreen(navController)
        }
        composable<Signup>{
            SignUpScreen(navController)
        }
        composable<Remember>{
            RememberScreen(navController)
        }
        composable<Home>{
            HomeScreen(navController)
        }
        composable<PracticeOne>{
            HomeScreen(navController)
        }
        composable<FilterBills>{
            HomeScreen(navController)
        }
        composable<PracticeTwo>{
            HomeScreen(navController)
        }
        composable<PracticeThree>{
            HomeScreen(navController)
        }
    }
}