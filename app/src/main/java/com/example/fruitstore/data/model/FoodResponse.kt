package com.example.fruitstore.data.model

import com.google.gson.annotations.SerializedName

data class FoodResponse(
    val title: String,
    @SerializedName("items")
    val foodItems: List<FoodItemResponse>
)

data class FoodItemResponse(
    val id: String,
    val name: String,
    val image: String,
    val color: String
)

data class FoodItemTextResponse(
    val id: String,
    val text: String
)
