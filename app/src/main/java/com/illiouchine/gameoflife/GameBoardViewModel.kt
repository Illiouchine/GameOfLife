package com.illiouchine.gameoflife

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameBoardViewModel: ViewModel() {

    private val _boardState : MutableStateFlow<Board> by lazy { MutableStateFlow(Board.withRandomCells()) }
    val boardState = _boardState.asStateFlow()

    fun touch(coordinate: Coordinate){
        viewModelScope.launch {
            val newBoard = _boardState.value.touch(coordinate)
            _boardState.value = newBoard
        }
    }

    fun tick(){
        viewModelScope.launch {
            val newBoard = _boardState.value.tick()
            _boardState.value = newBoard
        }
    }

    fun restartWithAlive() {
        viewModelScope.launch {
            val newBoard = Board.withAliveCells(_boardState.value.boardSize)
            _boardState.value = newBoard
        }
    }

    fun restartWithRandom() {
        viewModelScope.launch {
            val newBoard = Board.withRandomCells(_boardState.value.boardSize)
            _boardState.value = newBoard
        }
    }

    fun restartWithDead() {
        viewModelScope.launch {
            val newBoard = Board.withDeadCells(_boardState.value.boardSize)
            _boardState.value = newBoard
        }
    }


}