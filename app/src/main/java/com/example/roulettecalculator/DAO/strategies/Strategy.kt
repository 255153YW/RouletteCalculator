package com.example.roulettecalculator.DAO.strategies

import com.example.roulettecalculator.DAO.RouletteNumber
import com.example.roulettecalculator.DAO.RouletteTable

open class Strategy {
    protected val rouletteTable = RouletteTable()
    open val strategyName:String = ""
    open val selectedNumbers:List<RouletteNumber> = listOf()
}