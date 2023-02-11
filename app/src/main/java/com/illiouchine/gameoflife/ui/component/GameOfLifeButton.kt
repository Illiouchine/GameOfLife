package com.illiouchine.gameoflife.ui.component

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.tooling.preview.Preview
import com.illiouchine.gameoflife.ui.theme.GameOfLifeTheme

@Preview
@Composable
fun GameOfLifeButton(
    onClick : () -> Unit = {},
    colors: GameOfLifeButtonColors = GameOfLifeButtonColors.Primary,
    content: @Composable () -> Unit = { Text(text = "Click") }
) {
    val buttonColors: ButtonColors = when(colors){
        GameOfLifeButtonColors.Primary -> ButtonDefaults.buttonColors(
            containerColor = GameOfLifeTheme.colors.primaryContent,
            contentColor = GameOfLifeTheme.colors.onPrimary,
            disabledContainerColor = GameOfLifeTheme.colors.primaryContent
                .copy(alpha = 0.12f)
                .compositeOver(GameOfLifeTheme.colors.primaryContent),
            disabledContentColor = GameOfLifeTheme.colors.onPrimary
                .copy(alpha = 0.2f)
        )
        GameOfLifeButtonColors.Secondary -> ButtonDefaults.buttonColors(
            containerColor = GameOfLifeTheme.colors.secondaryContent,
            contentColor = GameOfLifeTheme.colors.onPrimary,
            disabledContainerColor = GameOfLifeTheme.colors.secondaryContent
                .copy(alpha = 0.12f)
                .compositeOver(GameOfLifeTheme.colors.secondaryContent),
            disabledContentColor = GameOfLifeTheme.colors.onPrimary
                .copy(alpha = 0.2f)
        )
        GameOfLifeButtonColors.Tertiary -> ButtonDefaults.buttonColors(
            containerColor = GameOfLifeTheme.colors.tertiaryContent,
            contentColor = GameOfLifeTheme.colors.onPrimary,
            disabledContainerColor = GameOfLifeTheme.colors.tertiaryContent
                .copy(alpha = 0.12f)
                .compositeOver(GameOfLifeTheme.colors.tertiaryContent),
            disabledContentColor = GameOfLifeTheme.colors.onPrimary
                .copy(alpha = 0.2f)
        )
    }

    Button(
        colors = buttonColors,
        shape = GameOfLifeTheme.shapes.button,
        onClick = { onClick() },
    ) {
        content()
    }
}

sealed class GameOfLifeButtonColors{
    object Primary : GameOfLifeButtonColors()
    object Secondary : GameOfLifeButtonColors()
    object Tertiary : GameOfLifeButtonColors()
}
