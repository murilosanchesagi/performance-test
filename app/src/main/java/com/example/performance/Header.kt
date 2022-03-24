package com.example.performance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.performance.ui.theme.Purple200
import com.example.performance.ui.theme.Purple700
import com.example.performance.ui.theme.Size
import com.example.performance.ui.theme.Teal200
import com.example.performance.ui.theme.White

@Composable
fun Header(headerBalanceVisible: Boolean) {
    if (headerBalanceVisible) {
        Header(
            title = "Saldo",
            backgroundColor = White,
            contentColor = Purple700
        )
    } else {
        Header(
            title = "Avatar",
            backgroundColor = White,
            contentColor = Purple700
        )
    }
}

@Composable
private fun Header(
    modifier: Modifier = Modifier,
    title: String,
    fontIconStart: String? = null,
    fontIconEnd: String? = null,
    contentDescriptionIconStart: String? = null,
    contentDescriptionIconEnd: String? = null,
    fontIconStartClick: () -> Unit = {},
    fontIconEndClick: () -> Unit = {},
    progressMax: Int? = null,
    progressStep: Int? = null,
    progressColor: Color = Teal200,
    progressBackgroundColor: Color = Purple700,
    backgroundColor: Color = Purple200,
    contentColor: Color = Purple700,
    elevation: Dp = Size.Size0
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        contentPadding = PaddingValues(Size.Size0),
        elevation = elevation
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            HeaderContent(
                title = title,
                fontIconStart = fontIconStart,
                fontIconEnd = fontIconEnd,
                fontIconStartClick = fontIconStartClick,
                fontIconEndClick = fontIconEndClick,
                contentColor = contentColor,
                contentDescriptionIconStart = contentDescriptionIconStart,
                contentDescriptionIconEnd = contentDescriptionIconEnd
            )
            HeaderProgress(
                modifier = Modifier.align(Alignment.BottomCenter),
                progressMax = progressMax,
                progressStep = progressStep,
                progressColor = progressColor,
                progressBackgroundColor = progressBackgroundColor
            )
        }
    }
}

@Composable
private fun HeaderContent(
    title: String,
    fontIconStart: String?,
    fontIconEnd: String?,
    fontIconStartClick: () -> Unit,
    fontIconEndClick: () -> Unit,
    contentColor: Color,
    contentDescriptionIconStart: String?,
    contentDescriptionIconEnd: String?
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Size.SizeXSM),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HeaderIcon(
            fontIcon = fontIconStart,
            color = contentColor,
            onClick = fontIconStartClick,
            contentDescription = contentDescriptionIconStart
        )
        SpacerHorizontal(Size.SizeXSM)
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            textAlign = TextAlign.Center,
            color = contentColor
        )
        SpacerHorizontal(Size.SizeXSM)
        HeaderIcon(
            fontIcon = fontIconEnd,
            color = contentColor,
            onClick = fontIconEndClick,
            contentDescription = contentDescriptionIconEnd
        )
    }
}

@Composable
private fun HeaderIcon(
    fontIcon: String?,
    color: Color,
    onClick: () -> Unit,
    contentDescription: String?
) {
    val size = Size.SizeXLG

    if (!fontIcon.isNullOrEmpty()) {
        Box(
            modifier = Modifier
                .size(size)
                .semantics {
                    this.role = Role.Button
                    contentDescription?.let { this.contentDescription = contentDescription }
                }
                .rippleClickable(
                    shape = CircleShape,
                    onClick = onClick
                ),
            contentAlignment = Alignment.Center
        ) {
            IconText(
                fontIcon = fontIcon,
                iconSize = Size.SizeLG,
                color = color
            )
        }
    } else {
        SpacerHorizontal(size)
    }
}

@Composable
private fun IconText(
    fontIcon: String,
    iconSize: Dp,
    color: Color = Purple200
) {
    Text(
        text = fontIcon,
        color = color,
        fontSize = iconSize.sp()
    )
}

@Composable
private fun HeaderProgress(
    modifier: Modifier = Modifier,
    progressMax: Int?,
    progressStep: Int?,
    progressColor: Color,
    progressBackgroundColor: Color
) {
    if (progressMax != null && progressStep != null) {
        LinearProgressIndicatorStep(
            modifier = modifier
                .fillMaxWidth(),
            progressMax = progressMax,
            progressStep = progressStep,
            color = progressColor,
            backgroundColor = progressBackgroundColor
        )
    }
}

@Preview
@Composable
private fun HeaderPreview() {
    Header(
        title = "Preview",
        backgroundColor = Purple200,
        contentColor = Purple700
    )
}