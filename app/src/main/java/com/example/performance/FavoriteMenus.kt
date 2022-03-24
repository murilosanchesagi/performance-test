package com.example.performance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.example.performance.ui.theme.Purple200
import com.example.performance.ui.theme.Purple500
import com.example.performance.ui.theme.Size

@Composable
fun FavoriteMenus() {
    Box(modifier = Modifier.height(IntrinsicSize.Min)) {
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = Size.SizeSM)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Purple200,
                            Purple500
                        )
                    )
                )
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "Hello!")
        }
    }
}