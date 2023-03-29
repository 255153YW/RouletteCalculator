package com.example.roulettecalculator.DAO

import com.example.roulettecalculator.DAO.strategies.*

class RouletteStrategyList {
    val strategy = listOf<Strategy>(
        Red(),
        Black(),
        BlackSnakeHalf(),
        RedSnakeHalf()
    )
}