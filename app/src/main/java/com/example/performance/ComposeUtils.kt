package com.example.performance

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.example.performance.ui.theme.Purple700
import com.example.performance.ui.theme.Size

@Composable
fun SpacerHorizontal(dp: Dp = Size.SizeSM) {
    Spacer(modifier = Modifier.width(dp))
}

@Composable
fun SpacerVertical(dp: Dp = Size.SizeSM) {
    Spacer(modifier = Modifier.height(dp))
}

@Composable
fun rippleEffect(
    color: Color = Purple700
) = rememberRipple(color = color)

fun Modifier.rippleClickable(
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = RectangleShape,
    enabled: Boolean = true,
    onClick: () -> Unit
) = composed {
    clip(shape).clickable(
        enabled = enabled,
        interactionSource = interactionSource ?: remember { MutableInteractionSource() },
        indication = rippleEffect(),
        onClick = onClick
    )
}

@Composable
fun Dp.sp() = with(LocalDensity.current) { this@sp.toSp() }
