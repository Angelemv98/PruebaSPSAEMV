package com.angelemv.android.pruebatecnicasps.views
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.angelemv.android.pruebatecnicasps.model.data.UserEntity
import com.angelemv.android.pruebatecnicasps.model.interfaces.RetrofitInstance.apiService
import com.angelemv.android.pruebatecnicasps.ui.theme.PruebaTecnicaSPSTheme
import com.angelemv.android.pruebatecnicasps.viewmodel.MainViewModel

@Composable
fun MainScreen(mainViewModel: MainViewModel = viewModel()) {
    val users by mainViewModel.users.collectAsState()
    if (users.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("No hay usuarios.")
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 32.dp)) {
            items(users) { user ->
                UserItem(user)
            }
        }
    }
}

@Composable
fun UserItem(user: UserEntity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = user.avatar,
            contentDescription = "Avatar de ${user.first_name}",
            modifier = Modifier.size(50.dp).clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = "Nombre: ${user.first_name}")
            Text(text = "Apellido: ${user.last_name}")
            Text(text = "Email: ${user.email}")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    PruebaTecnicaSPSTheme {
        MainScreen()
    }
}