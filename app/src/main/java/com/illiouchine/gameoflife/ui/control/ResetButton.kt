package com.illiouchine.gameoflife.ui.control

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.gameoflife.R
import com.illiouchine.gameoflife.ui.component.GameOfLifeButton
import com.illiouchine.gameoflife.ui.theme.GameOfLifeTheme
import com.illiouchine.gameoflife.ui.theme.backgroundColor

@Composable
fun ResetButton(
    modifier: Modifier = Modifier,
    onResetWithRandom: () -> Unit = {},
    onResetWithAlive: () -> Unit = {},
    onResetWithDead: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        var expanded by remember { mutableStateOf(false) }
        var selectedType: ResetType by remember { mutableStateOf(ResetType.Random) }


        Text(
            text = stringResource(selectedType.stringRes),
            color = GameOfLifeTheme.colors.onPrimary,
            modifier = Modifier
                .clickable(onClick = { expanded = !expanded })
                .padding(horizontal = 8.dp)
                .weight(4f)
        )
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                tint = GameOfLifeTheme.colors.onPrimary
            )
        }

        DropdownMenu(
            modifier = Modifier.background(color = GameOfLifeTheme.colors.background.first()),
            expanded = expanded,
            onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                modifier = Modifier.background(color = GameOfLifeTheme.colors.backgroundSpacer),
                colors = MenuDefaults.itemColors(
                    textColor = GameOfLifeTheme.colors.onBackground
                ),
                text = { Text(stringResource(ResetType.Random.stringRes)) },
                onClick = {
                    selectedType = ResetType.Random
                    expanded = false
                },
            )
            DropdownMenuItem(
                modifier = Modifier.background(color = GameOfLifeTheme.colors.background.first()),
                colors = MenuDefaults.itemColors(
                    textColor = GameOfLifeTheme.colors.onBackground
                ),
                text = { Text(stringResource(ResetType.Alive.stringRes)) },
                onClick = {
                    selectedType = ResetType.Alive
                    expanded = false
                },
            )
            DropdownMenuItem(
                modifier = Modifier.background(color = GameOfLifeTheme.colors.backgroundSpacer),
                colors = MenuDefaults.itemColors(
                    textColor = GameOfLifeTheme.colors.onBackground
                ),
                text = { Text(stringResource(ResetType.Dead.stringRes)) },
                onClick = {
                    selectedType = ResetType.Dead
                    expanded = false
                },
            )
        }
        Spacer(modifier = Modifier)
        GameOfLifeButton(
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.weight(3f),
            onClick = {
                when (selectedType) {
                    ResetType.Alive -> onResetWithAlive()
                    ResetType.Dead -> onResetWithDead()
                    ResetType.Random -> onResetWithRandom()
                }
            }
        ) {
            Text(text = stringResource(R.string.reset_button))
        }
    }
}

sealed class ResetType(@StringRes val stringRes: Int) {
    object Random : ResetType(R.string.reset_type_random)
    object Alive : ResetType(R.string.reset_type_alive)
    object Dead : ResetType(R.string.reset_type_dead)
}

@Preview(
    showBackground = true,
    backgroundColor = backgroundColor,
    locale = "fr"
)
@Composable
fun ResetButtonPreview() {
    ResetButton()
}