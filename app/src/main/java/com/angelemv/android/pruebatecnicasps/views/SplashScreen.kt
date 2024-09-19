package com.angelemv.android.pruebatecnicasps.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.angelemv.android.pruebatecnicasps.R
import com.angelemv.android.pruebatecnicasps.navigation.AppScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(nav: NavHostController) {

    LaunchedEffect(key1 = true) {
        delay(3000)
        nav.popBackStack()
        nav.navigate(AppScreens.MainScreen.route)
    }
    Splash()
}

@Composable
fun Splash() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_book),
            contentDescription = "App Logo",
            modifier = Modifier.size(180.dp)
        )
        Spacer(modifier = Modifier.height(46.dp))
        Text(
            "Morales Varela Angel Ernesto",
            fontSize = 30.sp,
            fontWeight = FontWeight.Thin,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    Splash()
}