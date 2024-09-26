package com.example.fruitstore

import android.app.Application
import com.example.fruitstore.data.repository.FoodRepositoryImpl
import com.example.fruitstore.data.source.FoodDataSource
import com.example.fruitstore.data.source.retrofit.FoodApiInstance
import com.example.fruitstore.domain.manager.FoodManager

class FruitStoreApplication : Application() {
    private lateinit var foodManager: FoodManager

    companion object {
        private lateinit var instance: FruitStoreApplication

        fun getInstance(): FruitStoreApplication {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        init()
    }

    private fun init() {
        val foodRepoImpl = FoodRepositoryImpl(FoodDataSource(FoodApiInstance.foodApiService))
        foodManager = FoodManager(foodRepoImpl)
    }

    fun getFoodManager(): FoodManager {
        return foodManager
    }
}