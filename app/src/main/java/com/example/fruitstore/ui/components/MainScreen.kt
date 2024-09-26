package com.example.fruitstore.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fruitstore.R
import com.example.fruitstore.domain.entity.FoodItem
import com.example.fruitstore.domain.entity.Foods
import com.example.fruitstore.ui.Utils
import com.example.fruitstore.ui.theme.FruitStoreTheme


const val ANIMATION_DURATION = 700

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onFoodItemClicked: (Int) -> Unit,
    onUpdateFoodButtonClicked: () -> Unit,
    foods: State<Foods?>
) {
    val appBarTitle = foods.value?.categoryName ?: ""
    FruitStoreTheme {
        Scaffold(
            topBar = {
                FruitStoreAppBar(
                    title = appBarTitle,
                    actions = {
                        IconButton(onClick = {
                            onUpdateFoodButtonClicked()
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.refresh_button),
                                contentDescription = "Update icon",
                                tint = Color.White,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    })
            }
        ) {
            if (foods.value != null) {
                FoodList(
                    Modifier
                        .fillMaxSize()
                        .padding(it),
                    foods = foods.value!!.foodItems,
                    onFoodItemClicked
                )
            } else {
                LoadingIndicator()
            }

        }
    }

}


@Composable
fun FoodList(
    modifier: Modifier = Modifier,
    foods: List<FoodItem>,
    onFoodItemClicked: (Int) -> Unit,
) {
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        isVisible = true
    }
    LazyColumn(
        modifier.padding(horizontal = 34.dp),
    ) {
        itemsIndexed(foods) { index, foodItem ->
            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn(
                    animationSpec = tween(durationMillis = ANIMATION_DURATION, easing = FastOutSlowInEasing)
                ) + slideInVertically(
                    initialOffsetY = { -it / 4 },
                    animationSpec = tween(
                        durationMillis = ANIMATION_DURATION + index * 100,
                        easing = FastOutSlowInEasing
                    )
                )
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Spacer(
                        modifier = Modifier
                            .height(24.dp)
                            .fillMaxWidth()
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Utils.hexStringToColor(foodItem.color),
                                shape = RoundedCornerShape(18.dp)
                            )
                            .height(90.dp)
                            .padding(horizontal = 20.dp, vertical = 5.dp)
                            .clickable {
                                onFoodItemClicked(index)
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                    ) {
                        Text(
                            text = foodItem.name,
                            color = Color.White,
                            modifier = Modifier.weight(1f),
                            fontSize = 22.sp
                        )
                        NetworkImage(
                            url = foodItem.image,
                            modifier = Modifier.height(75.dp)
                        )
                    }
                }
            }

        }
    }
}


@Preview
@Composable
fun mainPreview() {
    FoodList(
        modifier = Modifier.padding(36.dp),
        foods =
        listOf(
            FoodItem("1", "Apple", "", "1D3461"),
            FoodItem("2", "Pear", "", "3D3111"),
            FoodItem("3", "Orange", "", "773461")
        ),
        {}
    )

}