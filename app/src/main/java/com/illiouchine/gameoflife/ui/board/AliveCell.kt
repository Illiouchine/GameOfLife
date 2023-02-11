package com.illiouchine.gameoflife.ui.board

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
import com.illiouchine.gameoflife.ui.theme.backgroundColor


fun DrawScope.drawAliveCell(cell: Map.Entry<Coordinate, Cell>, cellSize: Float, padding: Float = 0f) {
    drawRect(
        brush = Brush.radialGradient(
            Pair(0.07f, Color(37f/255f, 172f/255f, 228f/255f, 1.0f)),
            Pair(0.08f, Color(134f/255f, 33f/255, 90f/255, 1.0f)),
            Pair(0.22f, Color(134f/255f, 33f/255, 90f/255, 1.0f)),
            Pair(0.26f, Color(233f/255f, 103f/255f, 176f/255f, 1.0f)),
            Pair(0.27f, Color(47f/255, 79f/255, 83f/255, 1.0f)),
            Pair(0.50f, Color(0f/255f, 255f/255, 138f/255, 0.8f)),
            Pair(0.73f, Color(60f/255, 95f/255, 18f/255, 0.32f)),
            Pair(0.90f, Color(1.0f, 1.0f, 1.0f, 0.0f)),
            center = Offset(
                (((cellSize * cell.key.x.toFloat()) + cellSize / 2)+padding),
                (((cellSize * cell.key.y.toFloat()) + cellSize / 2)+padding)
            ),
            radius = cellSize *0.5f
        ),
        topLeft = Offset(
            ((cellSize * cell.key.x.toFloat()) + padding),
            ((cellSize * cell.key.y.toFloat()) + padding)
        ),
        size = Size(cellSize, cellSize),
        style = Fill,
    )
}

@Preview(
    showBackground = true,
    backgroundColor = backgroundColor
)
@Composable
fun AliveCellPreview() {
    val cells = mapOf(
        Pair(Coordinate(0, 0), Cell(Cell.State.Alive)),
        Pair(Coordinate(1, 0), Cell(Cell.State.Alive)),
        Pair(Coordinate(1, 1), Cell(Cell.State.Alive))
    )
    Canvas(modifier = Modifier.size(100.dp), onDraw = {
        cells.forEach {
            drawAliveCell(
                cell = it,
                cellSize = 50.dp.toPx(),
                10f
            )
        }
    })
}