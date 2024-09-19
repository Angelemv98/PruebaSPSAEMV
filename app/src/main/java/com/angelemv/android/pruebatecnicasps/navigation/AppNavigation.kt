package com.angelemv.android.pruebatecnicasps.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.angelemv.android.pruebatecnicasps.views.AddNewUserScreen
import com.angelemv.android.pruebatecnicasps.views.MainScreen
import com.angelemv.android.pruebatecnicasps.views.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route) {
        composable(AppScreens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(AppScreens.MainScreen.route) {
            MainScreen(navController)
        }
        composable(AppScreens.AddNewUser.route) {
            AddNewUserScreen(navController, viewModel())
        }
    }
}