package com.illiouchine.gameoflife.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.gameoflife.model.Control
import com.illiouchine.gameoflife.ui.component.GameOfLifeButton
import com.illiouchine.gameoflife.ui.component.GameOfLifeButtonColors
import com.illiouchine.gameoflife.ui.component.GameOfLifeSlider
import com.illiouchine.gameoflife.ui.theme.GameOfLifeTheme
import com.illiouchine.gameoflife.ui.theme.backgroundColor
import com.illiouchine.gameoflife.ui.theme.darkGreen
import com.illiouchine.gameoflife.ui.theme.mediumGreen

@Composable
fun ControlPanel(
    controlState: Control = Control(),
    restartWithAlive: () -> Unit = { },
    restartWithRandom: () -> Unit = { },
    restartWithDead: () -> Unit = { },
    playClick: () -> Unit = {},
    stopClick: () -> Unit = {},
    tickClick: () -> Unit = {},
    onGridSizeChange: (Int) -> Unit = {},
    enableGrid: (Boolean) -> Unit = {},
    onSpeedChange: (Control.Speed) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                when (controlState.timer) {
                    Control.Timer.Initial -> {
                        GameOfLifeButton(
                            colors = GameOfLifeButtonColors.Tertiary,
                            onClick = { playClick() },
                        ) {
                            Text(text = "Play")
                        }
                    }
                    Control.Timer.Running -> {
                        GameOfLifeButton(onClick = { stopClick() }) {
                            Text(text = "Stop")
                        }
                    }
                }
                GameOfLifeButton(onClick = { tickClick() }) {
                    Text(text = "Tick")
                }
                when (controlState.speed) {
                    Control.Speed.OneTime -> {
                        GameOfLifeButton(
                            colors = GameOfLifeButtonColors.Secondary,
                            onClick = { onSpeedChange(Control.Speed.TwoTime) }
                        ) {
                            Text(text = "speed x2")
                        }
                    }
                    Control.Speed.ThreeTime -> {
                        GameOfLifeButton(onClick = { onSpeedChange(Control.Speed.OneTime) }) {
                            Text(text = "speed x1")
                        }
                    }
                    Control.Speed.TwoTime -> {
                        GameOfLifeButton(onClick = { onSpeedChange(Control.Speed.ThreeTime) }) {
                            Text(text = "speed x3")
                        }
                    }
                }
            }
        }
        item { Spacer(modifier = Modifier.height(8.dp)) }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Reset : ")
                GameOfLifeButton(
                    onClick = { restartWithRandom() }
                ) {
                    Text(text = "Random")
                }
                Spacer(modifier = Modifier.width(4.dp))
                GameOfLifeButton(
                    onClick = { restartWithAlive() }
                ) {
                    Text(text = "Alive")
                }
                Spacer(modifier = Modifier.width(4.dp))
                GameOfLifeButton(
                    onClick = { restartWithDead() }
                ) {
                    Text(text = "Dead")
                }
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Grid : ")
                GameOfLifeButton(
                    colors = GameOfLifeButtonColors.Secondary,
                    onClick = { enableGrid(!controlState.gridEnabled) }
                ) {
                    if (controlState.gridEnabled) {
                        Text(text = "Hide")
                    } else {
                        Text(text = "Show")
                    }
                }
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(text = "size")

                var gridSize by remember { mutableStateOf(controlState.gridSize.toFloat()) }
                GameOfLifeSlider(
                    value = gridSize,
                    onValueChange = { newValue -> gridSize = newValue },
                    valueRange = 10f..100f,
                    steps = 10,
                    onValueChangeFinished = { onGridSizeChange(gridSize.toInt()) }
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = backgroundColor
)
@Composable
fun withTheme() {
    GameOfLifeTheme {
        ControlPanel()
    }
}