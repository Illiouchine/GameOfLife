package com.illiouchine.gameoflife.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.PointerInputScope
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
            .pointerInput(initialBoard.getBoardSize()) {
                detectTapGestures(
                    onTap = {
                        val (cellX, cellY) = calcCellPosition(
                            x = it.x,
                            y = it.y,
                            boardSize = initialBoard.getBoardSize()
                        )
                        onClick(cellX, cellY)
                    },
                )
            }
            .pointerInput(initialBoard.getBoardSize()) {
                detectDragGestures(
                    onDrag = { change: PointerInputChange, dragAmont ->
                        val (cellX, cellY) = calcCellPosition(
                            x = change.position.x,
                            y = change.position.y,
                            boardSize = initialBoard.getBoardSize()
                        )
                        onClick(cellX, cellY)
                    }
                )
            },
    ) {
        val relativeCellSize = calcRelativeCellSize(boardSize = initialBoard.getBoardSize())

        for (cell in initialBoard.cells) {
            if (cell.value.state == Cell.State.Alive) {
                drawAliveCell(cell, relativeCellSize)
            } else {
                drawDeadCell(cell, relativeCellSize)
            }
            if (showGrid) {
                drawGrid(cell, relativeCellSize)
            }
        }
    }
}

private fun DrawScope.calcRelativeCellSize(boardSize: Int): Float {
    val minSize = min(this.size.width, this.size.height)
    return minSize / boardSize.toFloat()
}

private fun PointerInputScope.calcCellPosition(x: Float, y: Float, boardSize: Int): Pair<Int, Int> {
    val minSize = min(this.size.width, this.size.height)
    val relativeCellSize: Float = minSize.toFloat() / boardSize.toFloat()

    val cellX = (x / relativeCellSize).toInt()
    val cellY = (y / relativeCellSize).toInt()

    return Pair(cellX, cellY)
}

@Preview
@Composable
fun GameOfLifePanelPreview() {
    GameOfLifeTheme {
        GameOfLifePanel(
            Modifier
                .fillMaxSize()
                .aspectRatio(1f)
                .background(Color.White),
            Board.withRandomCells(),
            false,
        ) { _, _ -> Unit }
    }
}