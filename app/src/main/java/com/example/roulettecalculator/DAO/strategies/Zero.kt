package com.example.roulettecalculator.DAO.strategies

class Zero : Strategy() {
    override val strategyName = "zero"
    override val selectedNumbers = listOf(
        rouletteTable.numbersOnTable.elementAt(0),
    )
}