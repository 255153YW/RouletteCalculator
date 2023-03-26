package com.example.roulettecalculator.DAO.strategies

class Red : Strategy() {
    override val strategyName = "red"
    override val selectedNumbers = listOf(
        rouletteTable.numbersOnTable.elementAt(1),
        rouletteTable.numbersOnTable.elementAt(3),
        rouletteTable.numbersOnTable.elementAt(5),
        rouletteTable.numbersOnTable.elementAt(7),
        rouletteTable.numbersOnTable.elementAt(9),
        rouletteTable.numbersOnTable.elementAt(12),
        rouletteTable.numbersOnTable.elementAt(14),
        rouletteTable.numbersOnTable.elementAt(16),
        rouletteTable.numbersOnTable.elementAt(18),
        rouletteTable.numbersOnTable.elementAt(21),
        rouletteTable.numbersOnTable.elementAt(23),
        rouletteTable.numbersOnTable.elementAt(25),
        rouletteTable.numbersOnTable.elementAt(27),
        rouletteTable.numbersOnTable.elementAt(28),
        rouletteTable.numbersOnTable.elementAt(30),
        rouletteTable.numbersOnTable.elementAt(32),
        rouletteTable.numbersOnTable.elementAt(34),
        rouletteTable.numbersOnTable.elementAt(36),
    )
}