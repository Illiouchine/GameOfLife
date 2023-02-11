package com.illiouchine.gameoflife.ui.control

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.gameoflife.ui.component.GameOfLifeButton
import com.illiouchine.gameoflife.ui.component.GameOfLifeButtonColors

@Composable
fun GridButton(
    gridEnabled: Boolean = true,
    onClick : (Boolean) -> Unit = {}
) {
    GameOfLifeButton(
        modifier = Modifier
            .size(44.dp)
            .aspectRatio(1f),
        colors = GameOfLifeButtonColors.Secondary,
        contentPadding = PaddingValues(12.dp),
        onClick = { onClick(!gridEnabled) }
    ) {
        GridIcon(gridEnabled)
    }
}

@Preview
@Composable
fun GridButtonPreview() {
    Row {
        GridButton(true)
        GridButton(false)
    }
}