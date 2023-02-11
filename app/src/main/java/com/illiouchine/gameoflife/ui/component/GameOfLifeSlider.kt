package com.illiouchine.gameoflife.ui.component

import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.illiouchine.gameoflife.ui.theme.GameOfLifeTheme

@Composable
fun GameOfLifeSlider(
    modifier: Modifier = Modifier,
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    steps: Int,
    onValueChange: (newValue: Float) -> Unit = { _ -> Unit },
    onValueChangeFinished: (() -> Unit)? = { }
) {
    Slider(
        modifier = modifier,
        value = value,
        onValueChange = { onValueChange(it) },
        valueRange = valueRange,
        steps = steps,
        colors = SliderDefaults.colors(
            inactiveTrackColor = GameOfLifeTheme.colors.primaryContentDark,
            activeTickColor = GameOfLifeTheme.colors.primaryContent,
            activeTrackColor = GameOfLifeTheme.colors.primaryContent,
            thumbColor = GameOfLifeTheme.colors.primaryContent
        ),
        onValueChangeFinished = { onValueChangeFinished?.invoke() },
    )
}