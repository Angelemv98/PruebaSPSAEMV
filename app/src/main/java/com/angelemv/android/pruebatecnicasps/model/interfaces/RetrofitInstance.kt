package com.angelemv.android.pruebatecnicasps.model.interfaces

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object RetrofitInstance {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://reqres.in/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java)
}