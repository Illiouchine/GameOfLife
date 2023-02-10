package com.illiouchine.gameoflife.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.gameoflife.model.Cell
import com.illiouchine.gameoflife.model.Coordinate


fun DrawScope.drawDeadCell(cell: Map.Entry<Coordinate, Cell>, cellSize: Float) {
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


@Preview
@Composable
fun DeadCellPreview() {
    val cells = mapOf(Pair(Coordinate(0, 0), Cell(Cell.State.Dead)))
    Canvas(modifier = Modifier.size(100.dp), onDraw = {
        drawDeadCell(
            cell = cells.entries.first(),
            cellSize = 200f
        )
    })
}
