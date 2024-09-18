package com.angelemv.android.pruebatecnicasps.model.interfaces

import com.angelemv.android.pruebatecnicasps.model.data.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("users")
    suspend fun getUsers(): UserResponse
}