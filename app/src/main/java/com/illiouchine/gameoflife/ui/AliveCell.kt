package com.illiouchine.gameoflife.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.gameoflife.model.Cell
import com.illiouchine.gameoflife.model.Coordinate


fun DrawScope.drawAliveCell(cell: Map.Entry<Coordinate, Cell>, cellSize: Float) {
    drawRect(
        brush = Brush.radialGradient(
            Pair(0.04f, Color(0.914f, 0.004f, 0.612f, 1.0f)),
            Pair(0.19f, Color(0.329f, 0.6f, 0.886f, 1.0f)),
            Pair(0.25f, Color(0.675f, 0.306f, 0.906f, 1.0f)),
            Pair(0.5f, Color(0.18f, 0.133f, 0.455f, 1.0f)),
            center = Offset(
                ((cellSize * cell.key.x.toFloat()) + cellSize / 2),
                ((cellSize * cell.key.y.toFloat()) + cellSize / 2)
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

@Preview
@Composable
fun AliveCellPreview() {
    val cells = mapOf(Pair(Coordinate(0, 0), Cell(Cell.State.Alive)))
    Canvas(modifier = Modifier.size(100.dp), onDraw = {
        drawAliveCell(
            cell = cells.entries.first(),
            cellSize = 200f
        )
    })
}