package com.illiouchine.gameoflife.ui.control

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.illiouchine.gameoflife.model.Control
import com.illiouchine.gameoflife.ui.component.GameOfLifeButton
import com.illiouchine.gameoflife.ui.component.GameOfLifeButtonColors

@Composable
fun SpeedButton(
    modifier: Modifier = Modifier,
    controlSpeed: Control.Speed = Control.Speed.OneTime,
    onSpeedChange: (Control.Speed) -> Unit = {},
) {
    when (controlSpeed) {
        Control.Speed.OneTime -> {
            GameOfLifeButton(
                modifier = modifier,
                colors = GameOfLifeButtonColors.Secondary,
                contentPadding = PaddingValues(4.dp),
                onClick = { onSpeedChange(Control.Speed.TwoTime) }
            ) {
                Text(text = "x2")
            }
        }
        Control.Speed.ThreeTime -> {
            GameOfLifeButton(
                modifier = modifier,
                colors = GameOfLifeButtonColors.Secondary,
                contentPadding = PaddingValues(4.dp),
                onClick = { onSpeedChange(Control.Speed.OneTime) }) {
                Text(text = "x1")
            }
        }
        Control.Speed.TwoTime -> {
            GameOfLifeButton(
                modifier = modifier,
                colors = GameOfLifeButtonColors.Secondary,
                contentPadding = PaddingValues(4.dp),
                onClick = { onSpeedChange(Control.Speed.ThreeTime) }) {
                Text(text = "x3")
            }
        }
    }
}