package com.example.fruitstore.data.source.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object FoodApiInstance {
    const val BASE_URL = "https://test-task-server.mediolanum.f17y.com"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val foodApiService: FoodApiService by lazy {
        retrofit.create(FoodApiService::class.java)
    }
}