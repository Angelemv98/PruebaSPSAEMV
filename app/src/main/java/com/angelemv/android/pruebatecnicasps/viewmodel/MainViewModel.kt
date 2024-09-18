package com.angelemv.android.pruebatecnicasps.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelemv.android.pruebatecnicasps.model.data.User
import com.angelemv.android.pruebatecnicasps.model.interfaces.RetrofitInstance.apiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    init {
        fetchUsers()
    }
    private fun fetchUsers() {
        viewModelScope.launch {
            val fetchedUsers = api()
            _users.value = fetchedUsers
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
