package com.example.roulettecalculator.DAO.strategies

import com.example.roulettecalculator.DAO.RouletteNumber

class Black : Strategy() {
    override val selectedNumbers = listOf(
        rouletteTable.table.elementAt(2),
        rouletteTable.table.elementAt(4),
        rouletteTable.table.elementAt(6),
        rouletteTable.table.elementAt(8),
        rouletteTable.table.elementAt(10),
        rouletteTable.table.elementAt(11),
        rouletteTable.table.elementAt(13),
        rouletteTable.table.elementAt(15),
        rouletteTable.table.elementAt(17),
        rouletteTable.table.elementAt(19),
        rouletteTable.table.elementAt(20),
        rouletteTable.table.elementAt(22),
        rouletteTable.table.elementAt(24),
        rouletteTable.table.elementAt(26),
        rouletteTable.table.elementAt(29),
        rouletteTable.table.elementAt(31),
        rouletteTable.table.elementAt(33),
        rouletteTable.table.elementAt(35),
    )
}