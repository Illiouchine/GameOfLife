package com.illiouchine.gameoflife.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.illiouchine.gameoflife.model.Control

@Composable
fun ControlPanel(
    controlState: Control,
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
                        Button(onClick = { playClick() }) {
                            Text(text = "Play")
                        }
                    }
                    Control.Timer.Running -> {
                        Button(onClick = { stopClick() }) {
                            Text(text = "Stop")
                        }
                    }
                }
                Button(onClick = { tickClick() }) {
                    Text(text = "Tick")
                }
                when (controlState.speed) {
                    Control.Speed.OneTime -> {
                        Button(onClick = { onSpeedChange(Control.Speed.TwoTime) }) {
                            Text(text = "speed x2")
                        }
                    }
                    Control.Speed.ThreeTime -> {
                        Button(onClick = { onSpeedChange(Control.Speed.OneTime) }) {
                            Text(text = "speed x1")
                        }
                    }
                    Control.Speed.TwoTime -> {
                        Button(onClick = { onSpeedChange(Control.Speed.ThreeTime) }) {
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
                Button(
                    onClick = { restartWithRandom() }
                ) {
                    Text(text = "Random")
                }
                Spacer(modifier = Modifier.width(4.dp))
                Button(
                    onClick = { restartWithAlive() }
                ) {
                    Text(text = "Alive")
                }
                Spacer(modifier = Modifier.width(4.dp))
                Button(
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
                Button(onClick = { enableGrid(!controlState.gridEnabled) }) {
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
                Slider(
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