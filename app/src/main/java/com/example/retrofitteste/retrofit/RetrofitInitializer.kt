package com.example.retrofitteste.retrofit

import com.example.retrofitteste.retrofit.service.Endpoints
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.0.101:3000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun receitaService() = retrofit.create(Endpoints::class.java)
}