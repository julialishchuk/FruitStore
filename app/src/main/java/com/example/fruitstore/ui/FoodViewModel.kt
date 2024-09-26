package com.example.fruitstore.ui

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fruitstore.FruitStoreApplication
import com.example.fruitstore.domain.entity.FoodItemFullInfo
import com.example.fruitstore.domain.entity.Foods
import com.example.fruitstore.domain.manager.FoodManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FoodViewModel : ViewModel() {
    private val emptyFood = Foods("", listOf())
    private val _food = mutableStateOf<Foods?>(null)
    val food: State<Foods?> get() = _food

    private val _selectedFood = mutableStateOf<FoodItemFullInfo?>(null)
    val selectedFood: State<FoodItemFullInfo?> get() = _selectedFood

    private var foodManager: FoodManager = FruitStoreApplication.getInstance().getFoodManager()

    init {
        updateAvailableFood()
    }

    fun selectFoodItem(position: Int) {
        _selectedFood.value = null
        viewModelScope.launch {
            delay(1500)
            val selectedFood = _food.value?.foodItems?.get(position)
            if (selectedFood != null) {
                _selectedFood.value = foodManager.getFoodItemDetails(selectedFood)
            }
        }

    }

    fun updateAvailableFood() {
        _food.value = null
        viewModelScope.launch {
            delay(1500)
            _food.value = foodManager.getAvailableFoods()
            Log.d(Utils.TAG, "Updated food: " + _food.value)
        }
    }
}