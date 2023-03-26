package com.example.roulettecalculator.adapters

import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.roulettecalculator.DAO.RouletteNumber
import com.example.roulettecalculator.R

class PlayedNumbersListViewAdapter(
    private val templateLayout: Int,
    private val playedNumberList: MutableList<RouletteNumber>
) : BaseAdapter() {

    private val lastClicked = ""

    override fun getCount(): Int = playedNumberList.size

    override fun getItem(position: Int): RouletteNumber = playedNumberList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(parent.context).inflate(templateLayout, parent, false)
        val textView = view.findViewById<TextView>(android.R.id.text1)
        val rouletteNumber = getItem(position)

        textView.text = rouletteNumber.number.toString()

        textView.setTextColor(ContextCompat.getColor(view.context,rouletteNumber.color))

        return view
    }
}