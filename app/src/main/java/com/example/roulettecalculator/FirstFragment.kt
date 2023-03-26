package com.example.roulettecalculator

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.inputmethodservice.InputMethodService
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
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

    private val rouletteStrategy = RouletteStrategy()

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
        val intValue = binding.editTextNumber.text.toString().toInt();
        if(intValue in 0..36) {
            playedNumber.add(rouletteTable.table.elementAt(intValue))
            binding.editTextNumber.setText("")
        }
        binding.textViewAttemptCount.text = playedNumber.count().toString()

        val mListView = binding.playedNumberList
        mListView.adapter = PlayedNumbersListViewAdapter(android.R.layout.simple_list_item_1, playedNumber)

//        view.clearFocus()
//        val imm = getSystemService(view.context, InputMethodService::class.java) as? InputMethodManager
//        imm?.hideSoftInputFromWindow(view.windowToken, 0)
//        val inputMethodManager = getSystemService(requireContext().applicationContext,InputMethodService::class.java) as InputMethodManager
//        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

    }
}
