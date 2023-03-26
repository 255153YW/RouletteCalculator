package com.example.roulettecalculator.DAO.strategies

import com.example.roulettecalculator.DAO.RouletteNumber

class Red : Strategy() {
    override val selectedNumbers = listOf(
        rouletteTable.table.elementAt(1),
        rouletteTable.table.elementAt(3),
        rouletteTable.table.elementAt(5),
        rouletteTable.table.elementAt(7),
        rouletteTable.table.elementAt(9),
        rouletteTable.table.elementAt(12),
        rouletteTable.table.elementAt(14),
        rouletteTable.table.elementAt(16),
        rouletteTable.table.elementAt(18),
        rouletteTable.table.elementAt(21),
        rouletteTable.table.elementAt(23),
        rouletteTable.table.elementAt(25),
        rouletteTable.table.elementAt(27),
        rouletteTable.table.elementAt(28),
        rouletteTable.table.elementAt(30),
        rouletteTable.table.elementAt(32),
        rouletteTable.table.elementAt(34),
        rouletteTable.table.elementAt(36),
    )
}