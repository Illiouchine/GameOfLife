package com.illiouchine.gameoflife

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.consumeDownChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.gameoflife.ui.theme.GameOfLifeTheme
import kotlin.math.min

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameBoardViewModel = GameBoardViewModel()

        setContent {
            GameOfLifeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val board = gameBoardViewModel.boardState.collectAsState()

                    Column {
                        GameOfLifePanel(
                            Modifier
                                .aspectRatio(1f)
                                .fillMaxWidth(),
                            board.value,
                            onClick = { x, y ->
                                gameBoardViewModel.touch(Coordinate(x, y))
                            }
                        )
                        Row(modifier = Modifier.padding(16.dp)) {
                            Text(text = "Reset with : ")
                            Button(
                                onClick = { gameBoardViewModel.restartWithAlive() }
                            ) {
                                Text(text = "Alive")
                            }
                            Button(
                                onClick = { gameBoardViewModel.restartWithRandom() }
                            ) {
                                Text(text = "Random")
                            }
                            Button(
                                onClick = { gameBoardViewModel.restartWithDead() }
                            ) {
                                Text(text = "Dead")
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(16.dp).fillMaxWidth()
                        ) {
                            Button(
                                onClick = { gameBoardViewModel.tick() }
                            ) {
                                Text(text = "Tick")
                            }
                        }
                    }
                }
            }
        }
    }
}
