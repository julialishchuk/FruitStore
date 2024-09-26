package com.example.fruitstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fruitstore.ui.FoodViewModel
import com.example.fruitstore.ui.components.FoodItemDetailsScreen
import com.example.fruitstore.ui.components.MainScreen
import com.example.fruitstore.ui.theme.FruitStoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FruitStoreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FruitStoreApp()
                }
            }
        }
    }

    @Composable
    fun FruitStoreApp() {
        val navController = rememberNavController()
        val viewModel: FoodViewModel = viewModel()

        NavHost(navController = navController, startDestination = "main") {
            composable("main") {
                MainScreen(
                    onFoodItemClicked = {
                        viewModel.selectFoodItem(it)
                        navController.navigate("details")
                    },
                    onUpdateFoodButtonClicked = {
                        viewModel.updateAvailableFood()
                    },
                    foods = viewModel.food
                )
            }

            composable("details",
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { it / 2 },
                        animationSpec = tween(500)
                    )
                }
            ) {
                FoodItemDetailsScreen(
                    onNavigateBack = {
                        navController.navigateUp()
                    },
                    selectedFood = viewModel.selectedFood
                )
            }
        }
    }

}
