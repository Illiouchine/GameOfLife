package com.illiouchine.gameoflife

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
                    color = GameOfLifeTheme.colors.background.first()
                ) {
                    val board by gameBoardViewModel.boardState.collectAsState()
                    val control by gameBoardViewModel.controlState.collectAsState()

                    Column {
                        GameOfLifePanel(
                            modifier = Modifier
                                .aspectRatio(1f)
                                .fillMaxWidth(),
                            initialBoard = board,
                            showGrid = control.gridEnabled,
                            onClick = { x, y ->
                                gameBoardViewModel.touch(Coordinate(x, y))
                            }
                        )
                        ControlPanel(
                            controlState = control,
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
