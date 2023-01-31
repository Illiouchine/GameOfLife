package com.illiouchine.gameoflife

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.illiouchine.gameoflife.model.Coordinate
import com.illiouchine.gameoflife.ui.ControlPanel
import com.illiouchine.gameoflife.ui.GameOfLifePanel
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
                            showGrid = control.value.gridEnabled,
                            onClick = { x, y ->
                                gameBoardViewModel.touch(Coordinate(x, y))
                            }
                        )
                        ControlPanel(
                            controlState = control.value,
                            restartWithAlive = { gameBoardViewModel.restartWithAlive() },
                            restartWithRandom = { gameBoardViewModel.restartWithRandom() },
                            restartWithDead = { gameBoardViewModel.restartWithDead() },
                            playClick = { gameBoardViewModel.play() },
                            stopClick = { gameBoardViewModel.stop() },
                            tickClick = { gameBoardViewModel.tick() },
                            onGridSizeChange = { gameBoardViewModel.changeGridSize(it) },
                            enableGrid = { gameBoardViewModel.enableGrid(it) },
                            onSpeedChange = { gameBoardViewModel.changeSpeed(it) }
                        )
                    }
                }
            }
        }
    }
}
