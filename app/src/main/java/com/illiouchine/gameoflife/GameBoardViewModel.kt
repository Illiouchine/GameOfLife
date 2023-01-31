package com.illiouchine.gameoflife

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameBoardViewModel: ViewModel() {

    private val _boardState : MutableStateFlow<Board> by lazy { MutableStateFlow(Board.withRandomCells()) }
    val boardState = _boardState.asStateFlow()

    private val _controlState : MutableStateFlow<Control> by lazy { MutableStateFlow(Control.Initial) }
    val controlState = _controlState.asStateFlow()

    private var timerJob: Job? =null

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

    fun stop() {
        viewModelScope.launch {
            timerJob?.cancelAndJoin()
            _controlState.value = Control.Initial
        }
    }

    fun play() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            _controlState.value = Control.Running
            while (isActive){
                tick()
                delay(1*1000)
            }
        }
    }
}