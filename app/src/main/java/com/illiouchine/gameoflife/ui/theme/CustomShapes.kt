package com.illiouchine.gameoflife.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Immutable
data class CustomShapes(
    val button: Shape
)

val LocalGameOfLifeShapes = staticCompositionLocalOf {
    CustomShapes(
        button = RoundedCornerShape(4.dp)
    )
}