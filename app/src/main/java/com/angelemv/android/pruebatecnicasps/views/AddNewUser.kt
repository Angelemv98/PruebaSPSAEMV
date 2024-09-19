package com.angelemv.android.pruebatecnicasps.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.angelemv.android.pruebatecnicasps.model.data.UserEntity
import com.angelemv.android.pruebatecnicasps.navigation.AppScreens
import com.angelemv.android.pruebatecnicasps.viewmodel.MainViewModel

@Composable
fun AddNewUserScreen(navController: NavController, viewModel: MainViewModel) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var avatar by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    )
    {
        Spacer(modifier = Modifier.height(46.dp))
        Text(
            "Agregar Nuevo Usuario",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 48.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = firstName,
            onValueChange = { firstName = it.capitalize() },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = lastName,
            onValueChange = { lastName = it.capitalize() },
            label = { Text("Apellido") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = phone,
            onValueChange = { newValue ->
                if (newValue.length <= 10 && newValue.all { it.isDigit() }) phone = newValue
            },
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = avatar,
            onValueChange = { avatar = it },
            label = { Text("URL de la imagen") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (email.contains("@") && email.contains(".") && phone.length <= 10) {
                    viewModel.addUser(
                        UserEntity(
                            id = 0, // Room gestionará el ID automáticamente
                            first_name = firstName,
                            last_name = lastName,
                            email = email,
                            phone = phone,
                            avatar = avatar
                        )
                    )
                    navController.navigate(AppScreens.MainScreen.route) {
                        popUpTo(AppScreens.MainScreen.route) { inclusive = true }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            enabled = firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank() && phone.isNotBlank() && avatar.isNotBlank()
        ) {
            Text("Guardar Usuario")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    AddNewUserScreen(rememberNavController(), viewModel())
}