package com.illiouchine.gameoflife.ui.control

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.gameoflife.ui.component.GameOfLifeButton
import com.illiouchine.gameoflife.ui.theme.GameOfLifeTheme
import com.illiouchine.gameoflife.ui.theme.backgroundColor

@Composable
fun ResetButton(
    modifier: Modifier = Modifier,
    onResetWithRandom: ()-> Unit = {},
    onResetWithAlive: ()-> Unit = {},
    onResetWithDead: ()-> Unit = {},
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically) {

        var expanded by remember{ mutableStateOf(false) }
        var selectedType: ResetType by remember{ mutableStateOf(ResetType.Random) }


        Text(
            text = selectedType.name,
            color = GameOfLifeTheme.colors.onPrimary,
            modifier = Modifier
                .clickable(onClick = { expanded = !expanded })
                .padding(horizontal = 8.dp)
                .weight(4.5f)
        )
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                tint = GameOfLifeTheme.colors.onPrimary)
        }

        DropdownMenu(
            modifier = Modifier,
            expanded = expanded,
            onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                text = { Text(ResetType.Random.name) },
                onClick = {
                    selectedType = ResetType.Random
                    expanded = false
                          },
            )
            DropdownMenuItem(
                text = { Text(ResetType.Alive.name) },
                onClick = { selectedType = ResetType.Alive
                    expanded = false},
            )
            DropdownMenuItem(
                text = { Text(ResetType.Dead.name) },
                onClick = { selectedType = ResetType.Dead
                    expanded = false},
            )
        }
        Spacer(modifier = Modifier)
        GameOfLifeButton(
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.weight(1.5f),
            onClick = {
                when(selectedType){
                    ResetType.Alive -> onResetWithAlive()
                    ResetType.Dead -> onResetWithDead()
                    ResetType.Random -> onResetWithRandom()
                }
            }
        ) {
            Text(text = "RESET")
        }
    }
}

sealed class ResetType(val name:String){
    object Random:ResetType("Random")
    object Alive:ResetType("Alive")
    object Dead:ResetType("Dead")
}

@Preview(
    showBackground = true,
    backgroundColor = backgroundColor,
)
@Composable
fun ResetButtonPreview() {
    ResetButton()
}