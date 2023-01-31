package com.illiouchine.gameoflife.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.gameoflife.model.Board
import com.illiouchine.gameoflife.model.Cell
import com.illiouchine.gameoflife.ui.theme.GameOfLifeTheme
import kotlin.math.min


@Composable
fun GameOfLifePanel(
    modifier: Modifier,
    initialBoard: Board,
    showGrid: Boolean,
    onClick: (x: Int, y: Int) -> Unit
) {
    Canvas(
        modifier = modifier
            .padding(16.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        // Do something onCompleted(tap.position.x)
                        val minSize = min(this.size.width, this.size.height)
                        val relativeCellSize = (minSize) / initialBoard.boardSize

                        val cellX = (it.x / relativeCellSize).toInt()
                        val cellY = (it.y / relativeCellSize).toInt()

                        onClick(cellX, cellY)
                    },
                )
            }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change: PointerInputChange, dragAmont ->
                        // Do something onCompleted(tap.position.x)
                        val minSize = min(this.size.width, this.size.height)
                        val relativeCellSize = (minSize) / initialBoard.boardSize

                        val cellX = (change.position.x / relativeCellSize).toInt()
                        val cellY = (change.position.y / relativeCellSize).toInt()

                        onClick(cellX, cellY)
                    }
                )
            },
    ) {
        val minSize = min(this.size.width, this.size.height)
        val relativeCellSize = minSize / initialBoard.boardSize

        for (cell in initialBoard.cells) {
            drawRect(
                color = if (cell.value.state == Cell.State.Alive) {
                    Color.Green
                } else {
                    Color.Yellow
                },
                topLeft = Offset(
                    (relativeCellSize * cell.key.x.toFloat()),
                    (relativeCellSize * cell.key.y.toFloat())
                ),
                size = Size(relativeCellSize, relativeCellSize),
                style = Fill,
            )
            if (showGrid) {
                drawRect(
                    color = Color.LightGray,
                    topLeft = Offset(
                        (relativeCellSize * cell.key.x.toFloat()),
                        (relativeCellSize * cell.key.y.toFloat())
                    ),
                    size = Size(relativeCellSize, relativeCellSize),
                    style = Stroke(1.dp.toPx()),
                )
            }
        }
    }
}

@Preview()
@Composable
fun GameOfLifePanelPreview() {
    GameOfLifeTheme {
        GameOfLifePanel(
            Modifier
                .fillMaxSize()
                .background(Color.White),
            Board.withRandomCells(),
            false,
        ) { _, _ -> Unit }
    }
}