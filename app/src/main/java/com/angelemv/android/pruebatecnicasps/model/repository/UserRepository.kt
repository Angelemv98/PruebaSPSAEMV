package com.angelemv.android.pruebatecnicasps.model.repository

import com.angelemv.android.pruebatecnicasps.model.data.User
import com.angelemv.android.pruebatecnicasps.model.data.UserDao
import com.angelemv.android.pruebatecnicasps.model.data.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val userDao: UserDao){
    suspend fun getAllUsers(): List<UserEntity> {
        return withContext(Dispatchers.IO) {
            userDao.getUsers()
        }
    }
}