package com.angelemv.android.pruebatecnicasps.navigation

sealed class AppScreens ( val route : String ) {
    object SplashScreen : AppScreens( "SplashScreen" )
    object MainScreen : AppScreens( "MainScreen" )

}