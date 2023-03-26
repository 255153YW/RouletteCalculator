package com.example.roulettecalculator.DAO.strategies

class BlackSnakeHalf : Strategy() {
    override val selectedNumbers = listOf(
        rouletteTable.numbersOnTable.elementAt(8),
        rouletteTable.numbersOnTable.elementAt(11),
        rouletteTable.numbersOnTable.elementAt(10),
        rouletteTable.numbersOnTable.elementAt(13),
        rouletteTable.numbersOnTable.elementAt(17),
        rouletteTable.numbersOnTable.elementAt(20),
        rouletteTable.numbersOnTable.elementAt(19),
        rouletteTable.numbersOnTable.elementAt(22),
        rouletteTable.numbersOnTable.elementAt(26),
        rouletteTable.numbersOnTable.elementAt(29),
    )
}