package com.illiouchine.gameoflife

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.illiouchine.gameoflife.model.Control

@Composable
fun ControlPanel(
    controlState: Control,
    restartWithAlive: ()->Unit = {  },
    restartWithRandom: ()->Unit = {  },
    restartWithDead: ()->Unit = {  },
    playClick: ()-> Unit= {},
    stopClick: ()-> Unit= {},
    tickClick:()-> Unit = {}
) {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Reset with : ")
        Button(
            onClick = { restartWithAlive() }
        ) {
            Text(text = "Alive")
        }
        Button(
            onClick = { restartWithRandom() }
        ) {
            Text(text = "Random")
        }
        Button(
            onClick = { restartWithDead() }
        ) {
            Text(text = "Dead")
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
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
    }
}