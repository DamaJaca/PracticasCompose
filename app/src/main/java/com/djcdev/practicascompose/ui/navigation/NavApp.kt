package com.djcdev.practicascompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.djcdev.practicascompose.ui.screens.filterscreen.FilterBills
import com.djcdev.practicascompose.ui.screens.firstscreen.BillsScreen
import com.djcdev.practicascompose.ui.screens.firstscreen.BillsViewModel
import com.djcdev.practicascompose.ui.screens.home.HomeScreen
import com.djcdev.practicascompose.ui.screens.login.LoginScreen
import com.djcdev.practicascompose.ui.screens.remember.RememberScreen
import com.djcdev.practicascompose.ui.screens.secondscreen.SecondScreen
import com.djcdev.practicascompose.ui.screens.signup.SignUpScreen


@Composable
fun NavApp () {

    val navController = rememberNavController()
    val facturasViewModel: BillsViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = Login){
        composable<Login>{
            LoginScreen(navController= navController)
        }
        composable<Signup>{
            SignUpScreen(navController= navController)
        }
        composable<Remember>{
            RememberScreen(navController= navController)
        }
        composable<Home>{
            HomeScreen(navController= navController)
        }
        composable<PracticeOne>{
            BillsScreen(navController= navController, facturasViewModel)
        }
        composable<FilterBills>{
            FilterBills(navController= navController, facturasViewModel)
        }
        composable<PracticeTwo>{
            SecondScreen(navController= navController)
        }
        composable<PracticeThree>{
            HomeScreen(navController= navController)
        }
    }
}