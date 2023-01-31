package com.illiouchine.gameoflife

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.illiouchine.gameoflife.model.Board
import com.illiouchine.gameoflife.model.Control
import com.illiouchine.gameoflife.model.Coordinate
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameBoardViewModel : ViewModel() {

    private val _boardState: MutableStateFlow<Board> by lazy { MutableStateFlow(Board.withRandomCells()) }
    val boardState = _boardState.asStateFlow()

    private val _controlState: MutableStateFlow<Control> by lazy { MutableStateFlow(Control(gridSize = 30)) }
    val controlState = _controlState.asStateFlow()

    private var timerJob: Job? = null
    private var tickDuration = TickDuration.OneTime

    enum class TickDuration(val duration: Long) {
        OneTime((1 * 1000).toLong()),
        TwoTime((0.5 * 1000).toLong()),
        ThreeTime((0.25 * 1000).toLong()),
    }

    fun touch(coordinate: Coordinate) {
        viewModelScope.launch {
            val newBoard = _boardState.value.touch(coordinate)
            _boardState.value = newBoard
        }
    }

    fun tick() {
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
            _controlState.value = _controlState.value.copy(timer = Control.Timer.Initial)
        }
    }

    fun play() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            _controlState.value = _controlState.value.copy(timer = Control.Timer.Running)
            while (isActive) {
                tick()
                delay(tickDuration.duration)
            }
        }
    }

    fun changeGridSize(boardSize: Int) {
        viewModelScope.launch {
            val newBoard = Board.withRandomCells(boardSize)
            _boardState.value = newBoard
        }
    }

    fun enableGrid(it: Boolean) {
        _controlState.value = _controlState.value.copy(gridEnabled = it)
    }

    fun changeSpeed(it: Control.Speed) {
        tickDuration = when (it) {
            Control.Speed.OneTime -> TickDuration.OneTime
            Control.Speed.TwoTime -> TickDuration.TwoTime
            Control.Speed.ThreeTime -> TickDuration.ThreeTime
        }
        _controlState.value = _controlState.value.copy(speed = it)
    }
}