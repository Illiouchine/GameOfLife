package com.illiouchine.gameoflife.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp

@Composable
fun GameOfLifeTheme(
    content: @Composable () -> Unit
) {

    val gameOfLifeColors = CustomColors(
        background = listOf(darkGreen),
        backgroundSpacer = spacerGreen,
        onBackground = white,
        primaryContent = lightGreen,
        primaryContentDark = mediumGreen,
        onPrimary = white,
        secondaryContent = lightBlue,
        secondaryContentDark = darkBlue,
        tertiaryContent = pink,
    )

    val gameOfLifeShapes = CustomShapes(
        button = RoundedCornerShape(4.dp)
    )

    CompositionLocalProvider(
        LocalGameOfLifeColors provides gameOfLifeColors,
        LocalGameOfLifeShapes provides gameOfLifeShapes,
        content = content
    )
}

object GameOfLifeTheme {
    val colors: CustomColors
        @Composable
        get() = LocalGameOfLifeColors.current
    val shapes: CustomShapes
        @Composable
        get() = LocalGameOfLifeShapes.current
}

