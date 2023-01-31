package com.illiouchine.gameoflife.model

data class Control(
    val timer : Timer = Timer.Initial,
    val gridSize : Int
){
    sealed class Timer{
        object Initial: Timer() // play tick
        object Running: Timer() // stop tick
    }
}