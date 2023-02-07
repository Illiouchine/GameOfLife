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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradient
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.gameoflife.model.Board
import com.illiouchine.gameoflife.model.Cell
import com.illiouchine.gameoflife.model.Coordinate
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
            if (cell.value.state == Cell.State.Alive){
                drawAliveCell(cell, relativeCellSize)
            }else {
                drawDeadCell(cell, relativeCellSize)
            }
            if (showGrid) {
                drawGrid(cell, relativeCellSize)
            }
        }
    }
}

private fun DrawScope.drawAliveCell(cell :Map.Entry<Coordinate, Cell>, cellSize: Float){
    drawRect(
        brush = Brush.radialGradient(
            Pair(0.04f, Color(0.914f, 0.004f, 0.612f, 1.0f)),
            Pair(0.19f, Color(0.329f, 0.6f, 0.886f, 1.0f)),
            Pair(0.25f, Color(0.675f, 0.306f, 0.906f, 1.0f)),
            Pair(0.5f, Color(0.18f, 0.133f, 0.455f, 1.0f)),
            center = Offset(
                ((cellSize * cell.key.x.toFloat()) + cellSize/2),
                ((cellSize * cell.key.y.toFloat())+ cellSize/2)
            ),
            radius = cellSize
        ),
        topLeft = Offset(
            (cellSize * cell.key.x.toFloat()),
            (cellSize * cell.key.y.toFloat())
        ),
        size = Size(cellSize, cellSize),
        style = Fill,
    )
}

private fun DrawScope.drawDeadCell(cell :Map.Entry<Coordinate, Cell>, cellSize: Float){
    drawRect(
        color = Color(0.18f, 0.133f, 0.455f, 1.0f),
        topLeft = Offset(
            (cellSize * cell.key.x.toFloat()),
            (cellSize * cell.key.y.toFloat())
        ),
        size = Size(cellSize, cellSize),
        style = Fill,
    )
}

private fun DrawScope.drawGrid(cell :Map.Entry<Coordinate, Cell>, cellSize: Float){
    drawRect(
        color = Color.LightGray,
        topLeft = Offset(
            (cellSize * cell.key.x.toFloat()),
            (cellSize * cell.key.y.toFloat())
        ),
        size = Size(cellSize, cellSize),
        style = Stroke(1.dp.toPx()),
    )
}

@Preview()
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