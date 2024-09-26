package com.example.fruitstore.data.source.retrofit

import com.example.fruitstore.data.model.FoodItemTextResponse
import com.example.fruitstore.data.model.FoodResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface FoodApiService {
    @GET("items/random")
    suspend fun getRandomFood(): FoodResponse

    @GET("texts/{itemId}")
    suspend fun getFoodItemText(
        @Path("itemId") foodItemId: String
    ): FoodItemTextResponse
}