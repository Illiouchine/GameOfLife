package com.illiouchine.gameoflife.ui.control

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.illiouchine.gameoflife.R
import com.illiouchine.gameoflife.model.Control
import com.illiouchine.gameoflife.ui.component.GameOfLifeButton
import com.illiouchine.gameoflife.ui.component.GameOfLifeButtonColors

@Composable
fun PlayButton(
    modifier: Modifier = Modifier,
    controlTimer: Control.Timer = Control.Timer.Initial,
    onPlayClicked: () -> Unit = {},
    onStopClicked: () -> Unit = {},
) {
    when (controlTimer) {
        Control.Timer.Initial -> {
            GameOfLifeButton(
                modifier = modifier,
                colors = GameOfLifeButtonColors.Tertiary,
                onClick = { onPlayClicked() },
            ) {
                Text(text = stringResource(R.string.play_button))
            }
        }
        Control.Timer.Running -> {
            GameOfLifeButton(
                modifier = modifier,
                onClick = { onStopClicked() }
            ) {
                Text(text = stringResource(R.string.stop_button))
            }
        }
    }
}