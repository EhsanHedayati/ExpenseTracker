package com.mylab.expensetracker.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mylab.expensetracker.R
import com.mylab.expensetracker.datamodel.Expense
import kotlinx.android.synthetic.main.fragment_expense_entry.view.*
import kotlinx.android.synthetic.main.fragment_income_entry.*
import kotlinx.android.synthetic.main.fragment_income_entry.view.*


class IncomeEntryFragment : Fragment() {
    private lateinit var expense: Expense
    private var receivedTime: Long? = null
    private lateinit var incomeEntryViewModel: IncomeEntryViewModel
    private var amountType: Boolean? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_income_entry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        amountType = IncomeEntryFragmentArgs.fromBundle(requireArguments()).amountType
        incomeEntryViewModel = ViewModelProvider(this).get(IncomeEntryViewModel::class.java)

        view.income_entry_date.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {

                incomeEntryViewModel.persianDatePicker(requireContext(), view)

            }
        }



        incomeEntryViewModel.longTime.observe(viewLifecycleOwner) {
            receivedTime = it
        }

        button_income_save.setOnClickListener {

            val title = income_entry_title.text.toString()
            val amount = income_entry_amount.text.toString()
            val date = income_entry_date.text.toString()
            val description = income_entry_description.text.toString()
            if (title.isNotEmpty() && amount.isNotEmpty() && date.isNotEmpty() && description.isNotEmpty()) {
                insertDataToDataBase()
                Toast.makeText(requireContext(), "با موفقیت ذخیره گردید.", Toast.LENGTH_SHORT)
                    .show()
                findNavController().navigateUp()

            } else {
                Toast.makeText(requireContext(), "لطفا همه فیلدها تکمیل شود", Toast.LENGTH_SHORT)
                    .show()
            }


        }


    }

    private fun insertDataToDataBase() {
        val title = income_entry_title.text.toString()
        val amount = income_entry_amount.text.toString().toLong()
        val date = this.receivedTime
        val description = income_entry_description.text.toString()
        val amountType = amountType
        expense = Expense(0, title, amount, date ?: 0, description, amountType ?: false)
        incomeEntryViewModel.insertExpense(expense)
    }


}