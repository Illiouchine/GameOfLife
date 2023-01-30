package com.illiouchine.gameoflife

import kotlin.random.Random

class Board private constructor(
    val boardSize: Int,
    val cells: Map<Coordinate, Cell> = emptyMap()
) {

    fun touch(coordinate: Coordinate): Board {
        val mutableCell = cells.toMutableMap()
        val cell = mutableCell[coordinate]
        if (cell != null) {
            mutableCell[coordinate] = if (cell.isAlive) {
                cell.copy(state = Cell.State.Dead)
            } else {
                cell.copy(state = Cell.State.Alive)
            }
            return Board(boardSize, mutableCell)
        }
        return this
    }

    fun tick():Board{
        val oldCells = cells.toMap()
        val newCells = mutableMapOf<Coordinate, Cell>()

        oldCells.forEach { (coordinate, cell) ->
            val aliveNeighbors = countAliveNeighbors(coordinate, oldCells)

            if (cell.state == Cell.State.Alive){
                //Any live cell with fewer than two live neighbours dies, as if by underpopulation.
                //Any live cell with two or three live neighbours lives on to the next generation.
                //Any live cell with more than three live neighbours dies, as if by overpopulation.
                if (aliveNeighbors < 2) {
                    newCells[coordinate] = cell.copy(state = Cell.State.Dead)
                } else if (aliveNeighbors == 2 || aliveNeighbors == 3) {
                    // Do nothing
                    newCells[coordinate] = cell.copy(state = Cell.State.Alive)
                } else {
                    newCells[coordinate] = cell.copy(state = Cell.State.Dead)
                }
            } else {
                //Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
                if (aliveNeighbors == 3){
                    newCells[coordinate] = cell.copy(state = Cell.State.Alive)
                }else {
                    newCells[coordinate] = cell
                }
            }
        }

        return Board(boardSize, newCells)
    }

    private fun countAliveNeighbors(coordinate: Coordinate, cells: Map<Coordinate, Cell>): Int {
        val neighbors: MutableMap<Coordinate, Cell> = mutableMapOf()
        for (neighborCoordinate in coordinate.getNeighborsCoordinate()){
            cells[neighborCoordinate]?.let {
                neighbors.put(neighborCoordinate, it)
            }
        }
        return neighbors.map { it.value.state }
            .count { it == Cell.State.Alive }
    }

    companion object {
        private const val BOARD_SIZE_DEFAULT : Int = 30

        fun withDeadCells(boardSize: Int = BOARD_SIZE_DEFAULT): Board {
            val initialCells: MutableMap<Coordinate, Cell> = mutableMapOf()
            for (x in 0 until boardSize) {
                for (y in 0 until boardSize) {
                    initialCells[Coordinate(x, y)] = Cell(Cell.State.Dead)
                }
            }
            return Board(boardSize, initialCells)
        }
        fun withAliveCells(boardSize: Int = BOARD_SIZE_DEFAULT): Board {
            val initialCells: MutableMap<Coordinate, Cell> = mutableMapOf()
            for (x in 0 until boardSize) {
                for (y in 0 until boardSize) {
                    initialCells[Coordinate(x, y)] = Cell(Cell.State.Alive)
                }
            }
            return Board(boardSize, initialCells)
        }
        fun withRandomCells(boardSize: Int = BOARD_SIZE_DEFAULT): Board {
            val initialCells: MutableMap<Coordinate, Cell> = mutableMapOf()
            for (x in 0 until boardSize) {
                for (y in 0 until boardSize) {
                    if (Random.nextBoolean()){
                        initialCells[Coordinate(x, y)] = Cell(Cell.State.Alive)
                    }else {
                        initialCells[Coordinate(x, y)] = Cell(Cell.State.Dead)
                    }
                }
            }
            return Board(boardSize, initialCells)
        }
    }
}

