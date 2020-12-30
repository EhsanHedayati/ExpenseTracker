package com.mylab.expensetracker.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mylab.expensetracker.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_income.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToIncomeEntryFragment(
                    1
                )
            )
        }
        button_expense.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToExpenseEntryFragment(
                    0
                )
            )
        }

        button_list.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_listFragment)
        }

        button_reports.setOnClickListener {

            findNavController().navigate(R.id.action_mainFragment_to_reportsFragment)

        }


    }
}