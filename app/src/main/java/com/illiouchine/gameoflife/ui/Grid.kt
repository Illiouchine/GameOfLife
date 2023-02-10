package com.illiouchine.gameoflife.ui

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.illiouchine.gameoflife.model.Cell
import com.illiouchine.gameoflife.model.Coordinate


fun DrawScope.drawGrid(cell: Map.Entry<Coordinate, Cell>, cellSize: Float) {
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
