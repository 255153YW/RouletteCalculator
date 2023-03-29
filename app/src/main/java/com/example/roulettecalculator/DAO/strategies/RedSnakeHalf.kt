package com.example.roulettecalculator.DAO.strategies

class RedSnakeHalf : Strategy() {
    override val strategyName = "redSnakeHalf"
    override val selectedNumbers = listOf(
        rouletteTable.numbersOnTable.elementAt(9),
        rouletteTable.numbersOnTable.elementAt(12),
        rouletteTable.numbersOnTable.elementAt(16),
        rouletteTable.numbersOnTable.elementAt(19),
        rouletteTable.numbersOnTable.elementAt(18),
        rouletteTable.numbersOnTable.elementAt(21),
        rouletteTable.numbersOnTable.elementAt(27),
        rouletteTable.numbersOnTable.elementAt(30),
    )
}