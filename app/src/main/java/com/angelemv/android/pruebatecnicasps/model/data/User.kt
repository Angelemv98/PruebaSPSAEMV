package com.angelemv.android.pruebatecnicasps.model.data

data class User(
    val id: Int,
    val first_name: String,
    val last_name: String,
    val avatar: String,
    val email: String
)

data class UserResponse(
    val data: List<User>
)