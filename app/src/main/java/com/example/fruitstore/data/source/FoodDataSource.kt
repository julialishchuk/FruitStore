package com.example.fruitstore.data.source

import android.util.Log
import com.example.fruitstore.data.model.FoodItemTextResponse
import com.example.fruitstore.data.model.FoodResponse
import com.example.fruitstore.data.source.retrofit.FoodApiService
import com.example.fruitstore.ui.Utils

class FoodDataSource(
    private val apiService: FoodApiService
) {

    suspend fun getRandomFood(): FoodResponse? {
        return try {
            apiService.getRandomFood()
        } catch (e: Exception) {
            Log.e(Utils.TAG, "Exception during FoodApiService call: $e")
            e.printStackTrace()
            return null
        }
    }

    suspend fun getFoodItemText(foodItemId: String): FoodItemTextResponse? {
        return try {
            apiService.getFoodItemText(foodItemId)
        } catch (e: Exception) {
            Log.e(Utils.TAG, "Exception during FoodApiService call: $e")
            e.printStackTrace()
            return null
        }
    }

}