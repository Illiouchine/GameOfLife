package com.illiouchine.gameoflife

sealed class Control {
    object Initial: Control() // play tick
    object Running: Control() // stop tick
}