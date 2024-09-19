package com.angelemv.android.pruebatecnicasps.model.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertUsers(users: List<UserEntity>)

    @Query("SELECT * FROM users")
     fun getUsers(): List<UserEntity>
}