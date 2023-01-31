package com.illiouchine.gameoflife.model

data class Cell(
    val state: State = State.Dead,
) {

    val isAlive: Boolean = state == State.Alive
    val isDead: Boolean = state == State.Dead

    enum class State {
        Dead, Alive
    }
}