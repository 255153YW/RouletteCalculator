package com.example.roulettecalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.navigation.fragment.findNavController
import com.example.roulettecalculator.DAO.RouletteNumber
import com.example.roulettecalculator.DAO.RouletteTable
import com.example.roulettecalculator.DAO.RouletteStrategy
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

    private val selectedNumbers = RouletteStrategy().strategy[2].selectedNumbers

    private val playedNumberList = mutableListOf<RouletteNumber>()

    private var lastWinIndex = 0

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editTextNumber.setOnEditorActionListener { v, actionId, event ->
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addPlayedNumber () {
        var editTextValueString = binding.editTextNumber.text.toString()
        if(editTextValueString.isEmpty() || editTextValueString.isBlank() || !editTextValueString.isDigitsOnly()) {
            editTextValueString = "-1"
        }
        val editTextValueInt = editTextValueString.toInt();
        if(editTextValueInt in 0..36) {
            val rouletteNumberPlayed = rouletteTable.table.elementAt(editTextValueInt)
            playedNumberList.add(rouletteNumberPlayed)
            binding.editTextNumber.setText("")
            val mListView = binding.playedNumberList
            mListView.adapter = PlayedNumbersListViewAdapter(android.R.layout.simple_list_item_1, playedNumberList)
            checkNumberWithStrategy(rouletteNumberPlayed)
        } else{
            Toast.makeText(requireContext().applicationContext, "invalid number entered", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkNumberWithStrategy (rouletteNumberPlayed:RouletteNumber) {
        val playedNumberListCount = playedNumberList.count()
        var attemptCount = playedNumberListCount.toString()
        val isNumberPlayedWin = selectedNumbers.any { it.number === rouletteNumberPlayed.number }
        val currentIndex = playedNumberListCount - 1
        if(isNumberPlayedWin) {
            attemptCount = "0"
            lastWinIndex = currentIndex + 1
        } else{
            val slicedList = playedNumberList.slice(lastWinIndex..currentIndex)
            attemptCount = slicedList.count().toString()
        }
        binding.textViewAttemptCount.text = attemptCount
    }
}
