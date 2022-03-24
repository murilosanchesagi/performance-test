package com.example.performance.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.example.performance.R

object Size {
    val Size0: Dp @Composable get() = dimensionResource(id = R.dimen.size_0)
    val SizeSM: Dp @Composable get() = dimensionResource(id = R.dimen.size_sm)
    val SizeLG: Dp @Composable get() = dimensionResource(id = R.dimen.size_lg)
    val Size3XLG: Dp @Composable get() = dimensionResource(id = R.dimen.size_3xlg)
    val SizeXSM: Dp @Composable get() = dimensionResource(id = R.dimen.size_xsm)
    val SizeXLG: Dp @Composable get() = dimensionResource(id = R.dimen.size_xlg)
}