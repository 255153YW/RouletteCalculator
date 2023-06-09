package com.example.roulettecalculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.roulettecalculator.DAO.RouletteNumber
import com.example.roulettecalculator.DAO.RouletteStrategyList
import com.example.roulettecalculator.DAO.RouletteTable
import com.example.roulettecalculator.adapters.PlayedNumbersListViewAdapter
import com.example.roulettecalculator.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val rouletteTable = RouletteTable()

    private val rouletteStrategyList = RouletteStrategyList().strategy

    private val playedNumberList = mutableListOf<RouletteNumber>()

    private var selectedStrategy = rouletteStrategyList[0]
    private var selectedNumbers = selectedStrategy.selectedNumbers
    private var loseChance = 0.toDouble()
    private var winChance = 0.toDouble()
    private var winChanceCumulative = winChance
    private var loseChanceCumulative = loseChance

    private var lastWinIndexPlusOne = 0

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editTextNumber.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    addPlayedNumber()
                    true
                }
                else -> false
            }
        }

        binding.buttonReset.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        val mSpinnerView = binding.strategyListSpinner
        val strategyListSpinnerAdapter = ArrayAdapter(
            requireContext().applicationContext,
            android.R.layout.simple_spinner_item,
            rouletteStrategyList.map { it.strategyName },
        )
        strategyListSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mSpinnerView.adapter = strategyListSpinnerAdapter
        mSpinnerView.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedStrategyName = parent?.getItemAtPosition(position)
                selectedStrategy = rouletteStrategyList.find{it.strategyName === selectedStrategyName}!!
                selectedNumbers = selectedStrategy.selectedNumbers
                initWinLoseChance()
            }
        }
        initWinLoseChance()
        setWinLoseChanceText(winChanceCumulative, loseChanceCumulative)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initWinLoseChance() {
        val selectedNumbersCount = selectedNumbers.count()
        val numbersOnTableCount = rouletteTable.numbersOnTable.count()
        loseChance = ((numbersOnTableCount.toDouble()-selectedNumbersCount.toDouble())/numbersOnTableCount.toDouble())
        winChance = 1.toDouble()-loseChance
        winChanceCumulative = winChance
        loseChanceCumulative = loseChance

        val playedNumberListCount = playedNumberList.count()
        if(playedNumberListCount > 0) {
            val currentIndex = playedNumberList.count()-1
            val selectedNumberArray = selectedNumbers.map{it.number}
            lastWinIndexPlusOne = playedNumberList.indexOfLast{selectedNumberArray.contains(it.number)}

            if(lastWinIndexPlusOne < 0){
                lastWinIndexPlusOne = 0
            } else {
                lastWinIndexPlusOne += 1
            }

            val slicedList = playedNumberList.slice(lastWinIndexPlusOne..currentIndex)

            val attemptCount = slicedList.count().toString()
            binding.textViewAttemptCount.text = attemptCount

            slicedList.forEach{ itFromSlicedList ->
                val isNumberPlayedWin = selectedNumbers.any { it.number == itFromSlicedList.number }
                calculateWinLoseChanceCumulative(isNumberPlayedWin)
            }
        }
        setWinLoseChanceText(winChanceCumulative, loseChanceCumulative)
    }

    private fun addPlayedNumber () {
        var editTextValueString = binding.editTextNumber.text.toString()
        if(editTextValueString.isEmpty() || editTextValueString.isBlank() || !editTextValueString.isDigitsOnly()) {
            editTextValueString = "-1"
        }
        val editTextValueInt = editTextValueString.toInt()
        if(editTextValueInt in 0..36) {
            val rouletteNumberPlayed = rouletteTable.numbersOnTable.elementAt(editTextValueInt)
            playedNumberList.add(rouletteNumberPlayed)
            binding.editTextNumber.setText("")
            val mListView = binding.playedNumberList
            mListView.adapter = PlayedNumbersListViewAdapter(android.R.layout.simple_list_item_1, playedNumberList.reversed())
            checkNumberWithStrategy(rouletteNumberPlayed)
        } else{
            Toast.makeText(requireContext().applicationContext, "invalid number entered", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkNumberWithStrategy (rouletteNumberPlayed:RouletteNumber) {
        val playedNumberListCount = playedNumberList.count()
        var attemptCount = "0"
        val isNumberPlayedWin = selectedNumbers.any { it.number == rouletteNumberPlayed.number }
        val currentIndex = playedNumberListCount - 1
        calculateWinLoseChanceCumulative(isNumberPlayedWin)
        if(isNumberPlayedWin) {
            lastWinIndexPlusOne = currentIndex + 1
            Toast.makeText(requireContext().applicationContext, "!!!WIN!!!", Toast.LENGTH_SHORT).show()
        } else{
            val slicedList = playedNumberList.slice(lastWinIndexPlusOne..currentIndex)
            attemptCount = slicedList.count().toString()
        }
        binding.textViewAttemptCount.text = attemptCount
    }

    private fun calculateWinLoseChanceCumulative (isNumberPlayedWin:Boolean) {
        if(isNumberPlayedWin) {
            winChanceCumulative = winChance
            loseChanceCumulative = loseChance
        } else {
            loseChanceCumulative *= loseChance
            winChanceCumulative = 1-loseChanceCumulative
        }
        setWinLoseChanceText(winChanceCumulative, loseChanceCumulative)
    }

    private fun setWinLoseChanceText(winChanceCumulative: Double, loseChanceCumulative: Double) {
        binding.textViewWinChance.text = (winChanceCumulative*100).toString()
        binding.textViewLoseChance.text = (loseChanceCumulative*100).toString()
    }
}
