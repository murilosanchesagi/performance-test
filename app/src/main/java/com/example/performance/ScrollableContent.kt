package com.example.performance

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.performance.ui.theme.Purple200
import com.example.performance.ui.theme.White

@Composable
fun ScrollableContent(hasMenus: Boolean = false) {
    Log.i("POC_TOOLBAR", "ScrollableContent")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White)
    ) {
        if (hasMenus) FavoriteMenus()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 300.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Container Banner",
                color = Purple200
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 500.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Container FastMenu",
                color = Purple200
            )
        }
    }
}