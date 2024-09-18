package com.angelemv.android.pruebatecnicasps.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.angelemv.android.pruebatecnicasps.views.MainScreen
import com.angelemv.android.pruebatecnicasps.views.SplashScreen

@Composable
fun AppNavigation(){
    val nav = rememberNavController()
    NavHost(navController = nav,
        startDestination = AppScreens.SplashScreen.route
        ){
        composable(AppScreens.SplashScreen.route){
            SplashScreen(nav)
        }
        composable(AppScreens.MainScreen.route){
            MainScreen()
        }

    }
}