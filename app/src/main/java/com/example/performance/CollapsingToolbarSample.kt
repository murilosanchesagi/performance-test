package com.example.performance

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import com.example.performance.ui.theme.Purple200
import com.example.performance.ui.theme.Purple500
import com.example.performance.ui.theme.Purple700
import com.example.performance.ui.theme.Size
import kotlin.math.roundToInt

private const val Weight1 = 1f

@Composable
fun CollapsingToolbarSample() {
    val lazyListState = rememberLazyListState()
    var scrolledY = 0f
    var previousOffset = 0
    val headerPx = with(LocalDensity.current) { Size.Size3XLG.roundToPx().toFloat() }
    val headerBalanceVisible = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyListState,
            contentPadding = PaddingValues(top = Size.Size3XLG)
        ) {
            item {
                CollapseContent(
                    hasMenus = false,
                    modifier = Modifier
                        .graphicsLayer {
                            scrolledY += lazyListState.firstVisibleItemScrollOffset - previousOffset
                            translationY = scrolledY * 0.5f
                            previousOffset = lazyListState.firstVisibleItemScrollOffset
                        },
                    labelModifier = Modifier
                        .onGloballyPositioned {
                            val headerHeight = headerPx.roundToInt()
                            val textHeight = it.size.height
                            val textY = it.positionInRoot().y
                            val y = ((textHeight + textY) - headerHeight).toInt()
                            headerBalanceVisible.value = y <= 0
                        }
                )
            }
            item {
                ScrollableContent(hasMenus = true)
            }
        }
        Header(headerBalanceVisible = headerBalanceVisible.value)
    }
}

@Composable
private fun CollapseContent(
    modifier: Modifier = Modifier,
    labelModifier: Modifier = Modifier,
    hasMenus: Boolean = false
) {
    Log.i("POC_TOOLBAR", "CollapseContent")
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Purple700,
                        Purple500
                    )
                )
            )
    ) {
        SpacerVertical(dp = Size.SizeLG)
        Text(
            modifier = labelModifier.padding(Size.SizeSM),
            text = "Saldo",
            color = Purple200
        )
        SpacerVertical(dp = Size.SizeLG)
        Spacer(modifier = Modifier.weight(Weight1))
        if (hasMenus) FavoriteMenus()
    }
}