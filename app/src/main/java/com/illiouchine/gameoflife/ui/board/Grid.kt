package com.illiouchine.gameoflife.ui.board

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import com.illiouchine.gameoflife.model.Cell
import com.illiouchine.gameoflife.model.Coordinate


fun DrawScope.drawGrid(cell: Map.Entry<Coordinate, Cell>, cellSize: Float, padding: Float = 10f) {
    drawRect(
        color = Color.LightGray,
        topLeft = Offset(
            (cellSize * cell.key.x.toFloat() + padding),
            (cellSize * cell.key.y.toFloat()+ padding)
        ),
        size = Size(cellSize, cellSize),
        style = Stroke(1f),
    )
}
