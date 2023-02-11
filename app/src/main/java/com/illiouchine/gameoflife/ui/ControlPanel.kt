package com.illiouchine.gameoflife.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.gameoflife.model.Control
import com.illiouchine.gameoflife.ui.component.GameOfLifeButton
import com.illiouchine.gameoflife.ui.component.GameOfLifeSlider
import com.illiouchine.gameoflife.ui.control.GridButton
import com.illiouchine.gameoflife.ui.control.PlayButton
import com.illiouchine.gameoflife.ui.control.ResetButton
import com.illiouchine.gameoflife.ui.control.SpeedButton
import com.illiouchine.gameoflife.ui.theme.GameOfLifeTheme
import com.illiouchine.gameoflife.ui.theme.backgroundColor

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
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        item {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                var gridSize by remember { mutableStateOf(controlState.gridSize.toFloat()) }
                GameOfLifeSlider(
                    modifier = Modifier.weight(12f),
                    value = gridSize,
                    onValueChange = { newValue -> gridSize = newValue },
                    valueRange = 10f..100f,
                    steps = 10,
                    onValueChangeFinished = { onGridSizeChange(gridSize.toInt()) }
                )
                Spacer(modifier = Modifier.width(16.dp))
                GridButton(
                    gridEnabled = controlState.gridEnabled,
                    onClick = { enableGrid(it) }
                )
            }
        }
        item {
            Spacer(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = GameOfLifeTheme.colors.backgroundSpacer))
        }
        item {
            ResetButton(
                onResetWithRandom = { restartWithRandom() },
                onResetWithAlive = { restartWithAlive() },
                onResetWithDead = { restartWithDead() },
            )
        }
        item {
            Spacer(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = GameOfLifeTheme.colors.backgroundSpacer))
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SpeedButton(
                    controlSpeed = controlState.speed,
                    modifier = Modifier.weight(1f),
                    onSpeedChange = { onSpeedChange(it) }
                )
                Spacer(modifier = Modifier.width(16.dp))
                GameOfLifeButton(
                    modifier = Modifier.weight(2f),
                    onClick = { tickClick() }
                ) {
                    Text(text = "TICK")
                }
                Spacer(modifier = Modifier.width(16.dp))
                PlayButton(
                    controlTimer = controlState.timer,
                    modifier = Modifier.weight(3f),
                    onPlayClicked = {playClick() },
                    onStopClicked = {stopClick() }
                )
            }
        }
        item {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
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