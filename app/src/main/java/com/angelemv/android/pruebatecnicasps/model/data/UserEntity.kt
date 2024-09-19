package com.angelemv.android.pruebatecnicasps.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Int,
    val first_name: String,
    val last_name: String,
    val email: String,
    val phone: String?,
    val avatar: String
)
