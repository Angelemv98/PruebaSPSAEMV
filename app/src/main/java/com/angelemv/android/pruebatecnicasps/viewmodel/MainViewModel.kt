package com.angelemv.android.pruebatecnicasps.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelemv.android.pruebatecnicasps.model.data.User
import com.angelemv.android.pruebatecnicasps.model.data.UserEntity
import com.angelemv.android.pruebatecnicasps.model.database.AppDataBase
import com.angelemv.android.pruebatecnicasps.model.interfaces.RetrofitInstance.apiService
import com.angelemv.android.pruebatecnicasps.model.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDataBase.getDatabase(application)
    private val userDao = db.userDao()

    private val _users = MutableStateFlow<List<UserEntity>>(emptyList())
    val users: StateFlow<List<UserEntity>> = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            val fetchedUsers = api()
            if (fetchedUsers.isNotEmpty()) {
                val userEntities = fetchedUsers.map { user ->
                    UserEntity(
                        id = user.id,
                        first_name = user.first_name,
                        last_name = user.last_name,
                        email = user.email,
                        phone = "",  // Ajusta según la lógica de tu API
                        avatar = user.avatar
                    )
                }
                withContext(Dispatchers.IO) {
                    userDao.insertUsers(userEntities)  // Insertar usuarios en la base de datos
                }
            }
            _users.value = withContext(Dispatchers.IO) { userDao.getUsers() }  // Cargar los usuarios desde la base de datos
        }
    }

    fun addUser(user: UserEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                userDao.insertUsers(listOf(user))
            }
            fetchUsers()
        }
    }
    private suspend fun api(): List<User> {
        return try {
            val response = apiService.getUsers()
            response.data
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
