package com.example.fruitstore.data.repository

import com.example.fruitstore.data.model.FoodResponse
import com.example.fruitstore.data.source.FoodDataSource
import com.example.fruitstore.data.source.retrofit.FoodApiInstance
import com.example.fruitstore.domain.entity.FoodItem
import com.example.fruitstore.domain.entity.FoodItemFullInfo
import com.example.fruitstore.domain.entity.Foods
import com.example.fruitstore.domain.repository.FoodRepository

class FoodRepositoryImpl(
    private val foodDataSource: FoodDataSource
) : FoodRepository {

    override suspend fun getAvailableFoods(): Foods? {
        val foodResponse = foodDataSource.getRandomFood()
        if (foodResponse != null) return convertFoodResponse(foodResponse)
        return null
    }

    private fun convertFoodResponse(foodResponse: FoodResponse): Foods {
        val items = foodResponse.foodItems.map {
            FoodItem(
                it.id,
                it.name,
                FoodApiInstance.BASE_URL + it.image,
                it.color
            )
        }
        return Foods(
            foodResponse.title,
            items
        )
    }

    override suspend fun getFoodItemDetails(foodItem: FoodItem): FoodItemFullInfo? {
        val text = foodDataSource.getFoodItemText(foodItem.id)?.text
        return if (text != null) {
            FoodItemFullInfo(
                foodItem,
                text
            )
        } else null
    }
}