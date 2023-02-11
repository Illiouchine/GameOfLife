package com.illiouchine.gameoflife.model

data class Control(
    val timer: Timer = Timer.Initial,
    val gridSize: Int = 30,
    val gridEnabled: Boolean = false,
    val speed: Speed = Speed.OneTime
) {
    sealed class Timer {
        object Initial : Timer() // play tick
        object Running : Timer() // stop tick
    }

    sealed class Speed {
        object OneTime : Speed()
        object TwoTime : Speed()
        object ThreeTime : Speed()
    }
}