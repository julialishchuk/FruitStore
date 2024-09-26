package com.example.fruitstore.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Refresh
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fruitstore.domain.entity.FoodItem
import com.example.fruitstore.domain.entity.FoodItemFullInfo
import com.example.fruitstore.ui.FoodViewModel
import com.example.fruitstore.ui.Utils
import com.example.fruitstore.ui.theme.FruitStoreTheme
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodItemDetailsScreen(
    onNavigateBack: () -> Unit,
    selectedFood: State<FoodItemFullInfo?>
) {
    val appBarTitle = selectedFood.value?.mainInfo?.name ?: ""

    FruitStoreTheme {
        Scaffold(
            topBar = {
                FruitStoreAppBar(
                    title = appBarTitle,
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                onNavigateBack()
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = "Back button",
                                tint = Color.White,
                                modifier = Modifier.size(40.dp)
                            )
                        }

                    })
            }
        ) {
            if (selectedFood.value != null) {
                FoodDetails(
                    Modifier
                        .padding(it),
                    foodItemFullInfo = selectedFood.value!!
                )
            }
            else {
                LoadingIndicator()
            }
        }
    }
}


@Composable
fun FoodDetails(
    modifier: Modifier = Modifier,
    foodItemFullInfo: FoodItemFullInfo
) {
    var alpha by remember { mutableStateOf(0f) }
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isVisible = true
        for (i in 50..100) {
            delay(1)
            alpha = i / 100f
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp, vertical = 20.dp)
            .graphicsLayer(alpha = alpha)
    ) {
        Column(
            modifier = modifier
                .background(
                    Utils.hexStringToColor(foodItemFullInfo.mainInfo.color),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(24.dp)
                .fillMaxWidth()
                .height(250.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NetworkImage(
                url = foodItemFullInfo.mainInfo.image,
                modifier = Modifier.height(85.dp)
            )
            Spacer(Modifier.size(20.dp))
            Text(
                text = foodItemFullInfo.details,
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }

}



@Preview
@Composable
fun detailsPreview() {
    FoodDetails(
        foodItemFullInfo = FoodItemFullInfo(
        FoodItem(
            "3", "Tomato", "", "125357"
        ),
        "Text for tomato. Itnkj kjglrkeg rgkglre rekgorekfn frllm rrgreg regbegtbgt rgrtthrgr."
    ))
    
}