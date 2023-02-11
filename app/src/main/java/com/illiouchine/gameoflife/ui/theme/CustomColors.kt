package com.illiouchine.gameoflife.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class CustomColors(
    val background: List<Color>,
    val backgroundSpacer: Color,
    val onBackground: Color,
    val primaryContent: Color,
    val primaryContentDark: Color,
    val onPrimary : Color,
    val secondaryContent: Color,
    val secondaryContentDark: Color,
    val tertiaryContent: Color,
)

val LocalGameOfLifeColors = staticCompositionLocalOf {
    CustomColors(
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
}