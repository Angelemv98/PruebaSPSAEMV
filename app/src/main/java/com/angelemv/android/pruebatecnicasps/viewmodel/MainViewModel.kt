package com.angelemv.android.pruebatecnicasps.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelemv.android.pruebatecnicasps.model.data.User
import com.angelemv.android.pruebatecnicasps.model.data.UserEntity
import com.angelemv.android.pruebatecnicasps.model.database.AppDataBase
import com.angelemv.android.pruebatecnicasps.model.interfaces.RetrofitInstance.apiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDataBase.getDatabase(application)
    private val userDao = db.userDao()
    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

    private val _users = MutableStateFlow<List<UserEntity>>(emptyList())
    val users: StateFlow<List<UserEntity>> = _users

    init {
        if (!hasDataBeenLoaded()) {
            fetchUsers()
        } else {
            viewModelScope.launch {
                _users.value = withContext(Dispatchers.IO) { userDao.getUsers() }
            }
        }
    }

    private fun hasDataBeenLoaded(): Boolean {
        return sharedPreferences.getBoolean("data_loaded", false)
    }

    private fun setDataLoaded() {
        with(sharedPreferences.edit()) {
            putBoolean("data_loaded", true)
            apply()
        }
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
                        phone = "",
                        avatar = user.avatar
                    )
                }
                withContext(Dispatchers.IO) {
                    userDao.insertUsers(userEntities)
                }
                setDataLoaded()
            }
            _users.value = withContext(Dispatchers.IO) { userDao.getUsers() }
        }
    }

    fun addUser(user: UserEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                userDao.insertUsers(listOf(user))
            }
            _users.value = withContext(Dispatchers.IO) { userDao.getUsers() }
        }
    }

    fun updateUser(user: UserEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                userDao.updateUser(user)
            }
            _users.value = withContext(Dispatchers.IO) { userDao.getUsers() }
        }
    }

    fun getUserById(id: Int): UserEntity? {
        return runBlocking {
            withContext(Dispatchers.IO) { userDao.getUserById(id) }
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