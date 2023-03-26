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

    private val rouletteStrategy = RouletteStrategy().strategy[2]

    private val playedNumber = mutableListOf<RouletteNumber>()


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
                    addPlayedNumber(view)
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

    private fun addPlayedNumber (view:View) {
        var editTextValueString = binding.editTextNumber.text.toString()
        if(editTextValueString.isEmpty() || editTextValueString.isBlank() || !editTextValueString.isDigitsOnly()) {
            editTextValueString = "-1"
        }
        val editTextValueInt = editTextValueString.toInt();
        if(editTextValueInt in 0..36) {
            playedNumber.add(rouletteTable.table.elementAt(editTextValueInt))
            binding.editTextNumber.setText("")
            binding.textViewAttemptCount.text = playedNumber.count().toString()
            val mListView = binding.playedNumberList
            mListView.adapter = PlayedNumbersListViewAdapter(android.R.layout.simple_list_item_1, playedNumber)
        } else{
            Toast.makeText(requireContext().applicationContext, "invalid number entered", Toast.LENGTH_SHORT).show()
        }
    }
}
