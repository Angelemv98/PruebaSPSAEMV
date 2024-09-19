package com.angelemv.android.pruebatecnicasps.views

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import com.angelemv.android.pruebatecnicasps.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.angelemv.android.pruebatecnicasps.model.data.UserEntity
import com.angelemv.android.pruebatecnicasps.navigation.AppScreens
import com.angelemv.android.pruebatecnicasps.ui.theme.PruebaTecnicaSPSTheme
import com.angelemv.android.pruebatecnicasps.viewmodel.MainViewModel

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel = viewModel()) {
    val users by mainViewModel.users.collectAsState()
    val context = LocalContext.current
    val backPressed = remember { mutableStateOf(false) }

    BackHandler {
        backPressed.value = true
    }

    if (backPressed.value) {
        AlertDialog(
            onDismissRequest = { backPressed.value = false },
            title = { Text("Confirmación") },
            text = { Text("¿Estás seguro de que quieres salir?") },
            confirmButton = {
                Button(onClick = {
                    (context as? Activity)?.finish()
                }) {
                    Text("Salir")
                }
            },
            dismissButton = {
                Button(onClick = {
                    backPressed.value = false
                }) {
                    Text("Cancelar")
                }
            }
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().
        padding(top = 64.dp)) {
            Text(
                text = "Lista de Usuarios",
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            if (users.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("No hay usuarios.")
                }
            } else {
                LazyColumn(modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 12.dp)) {
                    items(users) { user ->
                        UserItem(user, navController)
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = {
                navController.navigate(AppScreens.AddNewUser.route)
            },
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Agregar")
        }
    }
}

@Composable
fun UserItem(user: UserEntity, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        navController.navigate("${AppScreens.EditUser.route}/${user.id}")
                    }
                )
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (user.avatar?.isNotEmpty() == true && user.avatar != "null") {
            AsyncImage(
                model = user.avatar,
                contentDescription = "Avatar de ${user.first_name}",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.ic_no_image),
                contentDescription = "Imagen predeterminada",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = "Nombre: ${user.first_name}")
            Text(text = "Apellido: ${user.last_name}")
            Text(text = "Email: ${user.email}")
            if (user.phone?.isNotEmpty() == true) Text(text = "Telefono: ${user.phone}")
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    PruebaTecnicaSPSTheme {
        MainScreen(navController = rememberNavController())
    }
}