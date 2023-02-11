package com.illiouchine.gameoflife.ui.control

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke


@Composable
fun GridIcon(gridEnabled: Boolean) {
    val color = if (gridEnabled) {
        Color.DarkGray
    } else {
        Color.White
    }
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawRect(
            color = color,
            topLeft = Offset.Zero,
            size = this.size / 2f,
            style = Stroke(2f),
        )
        drawRect(
            color = color,
            topLeft = Offset(
                this.size.width / 2f,
                this.size.height / 2f
            ),
            size = this.size / 2f,
            style = Stroke(2f),
        )
        drawRect(
            color = color,
            topLeft = Offset.Zero,
            size = this.size,
            style = Stroke(2f),
        )
    }
}
