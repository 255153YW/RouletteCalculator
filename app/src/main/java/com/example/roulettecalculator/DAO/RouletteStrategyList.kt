package com.example.roulettecalculator.DAO

import com.example.roulettecalculator.DAO.strategies.Black
import com.example.roulettecalculator.DAO.strategies.BlackSnakeHalf
import com.example.roulettecalculator.DAO.strategies.Red
import com.example.roulettecalculator.DAO.strategies.Strategy

class RouletteStrategyList {
    val strategy = listOf<Strategy>(
        Red(),
        Black(),
        BlackSnakeHalf(),
    )
}