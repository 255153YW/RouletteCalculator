package com.example.roulettecalculator.DAO.strategies

class Black : Strategy() {
    override val selectedNumbers = listOf(
        rouletteTable.numbersOnTable.elementAt(2),
        rouletteTable.numbersOnTable.elementAt(4),
        rouletteTable.numbersOnTable.elementAt(6),
        rouletteTable.numbersOnTable.elementAt(8),
        rouletteTable.numbersOnTable.elementAt(10),
        rouletteTable.numbersOnTable.elementAt(11),
        rouletteTable.numbersOnTable.elementAt(13),
        rouletteTable.numbersOnTable.elementAt(15),
        rouletteTable.numbersOnTable.elementAt(17),
        rouletteTable.numbersOnTable.elementAt(19),
        rouletteTable.numbersOnTable.elementAt(20),
        rouletteTable.numbersOnTable.elementAt(22),
        rouletteTable.numbersOnTable.elementAt(24),
        rouletteTable.numbersOnTable.elementAt(26),
        rouletteTable.numbersOnTable.elementAt(29),
        rouletteTable.numbersOnTable.elementAt(31),
        rouletteTable.numbersOnTable.elementAt(33),
        rouletteTable.numbersOnTable.elementAt(35),
    )
}