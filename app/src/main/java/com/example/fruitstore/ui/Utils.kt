package com.example.fruitstore.ui

import androidx.compose.ui.graphics.Color

class Utils {
    companion object {
        const val TAG = "FruitStore"

        fun hexStringToColor(hex: String): Color {
            return Color(android.graphics.Color.parseColor("#$hex"))
        }
    }
}
