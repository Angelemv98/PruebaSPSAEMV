package com.angelemv.android.pruebatecnicasps.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.angelemv.android.pruebatecnicasps.model.data.UserDao
import com.angelemv.android.pruebatecnicasps.model.data.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context):AppDataBase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance

            }

        }
    }

}