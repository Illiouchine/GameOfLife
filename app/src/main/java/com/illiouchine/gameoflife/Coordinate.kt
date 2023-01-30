package com.illiouchine.gameoflife

data class Coordinate(val x: Int = 0, val y: Int = 0) {
    fun getNeighborsCoordinate(): List<Coordinate> {
        return listOf(
            Coordinate(0 + x, -1 + y),
            Coordinate(1 + x, -1 + y),
            Coordinate(1 + x, 0 + y),
            Coordinate(1 + x, 1 + y),
            Coordinate(0 + x, 1 + y),
            Coordinate(-1 + x, 1 + y),
            Coordinate(-1 + x, 0 + y),
            Coordinate(-1 + x, -1 + y),
        )
    }
}