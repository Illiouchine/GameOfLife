package com.illiouchine.gameoflife

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.illiouchine.gameoflife.ui.theme.GameOfLifeTheme

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
                    val control = gameBoardViewModel.controlState.collectAsState()

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
                        ControlPanel(
                            controlState = control.value,
                            restartWithAlive = { gameBoardViewModel.restartWithAlive() },
                            restartWithRandom = { gameBoardViewModel.restartWithRandom() },
                            restartWithDead =  { gameBoardViewModel.restartWithDead() },
                            playClick = { gameBoardViewModel.play() },
                            stopClick = { gameBoardViewModel.stop() },
                            tickClick = { gameBoardViewModel.tick() }
                        )
                    }
                }
            }
        }
    }
}
