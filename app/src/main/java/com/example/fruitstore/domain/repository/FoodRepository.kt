package com.example.fruitstore.domain.repository

import com.example.fruitstore.domain.entity.FoodItem
import com.example.fruitstore.domain.entity.FoodItemFullInfo
import com.example.fruitstore.domain.entity.Foods

interface FoodRepository {

    suspend fun getAvailableFoods(): Foods?

    suspend fun getFoodItemDetails(foodItem: FoodItem): FoodItemFullInfo?
}