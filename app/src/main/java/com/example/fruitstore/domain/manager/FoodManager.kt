package com.example.fruitstore.domain.manager

import com.example.fruitstore.domain.entity.FoodItem
import com.example.fruitstore.domain.entity.FoodItemFullInfo
import com.example.fruitstore.domain.entity.Foods
import com.example.fruitstore.domain.repository.FoodRepository

class FoodManager(private val foodRepository: FoodRepository) {
    suspend fun getAvailableFoods(): Foods? {
        return foodRepository.getAvailableFoods()
    }

    suspend fun getFoodItemDetails(foodItem: FoodItem): FoodItemFullInfo? {
        if (foodItem.id.isNotEmpty()) {
            return foodRepository.getFoodItemDetails(foodItem)
        }
        return null
    }
}