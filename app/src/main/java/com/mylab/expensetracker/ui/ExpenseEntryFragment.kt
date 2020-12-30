package com.mylab.expensetracker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mylab.expensetracker.R
import com.mylab.expensetracker.datamodel.Expense
import kotlinx.android.synthetic.main.fragment_expense_entry.*
import kotlinx.android.synthetic.main.fragment_expense_entry.view.*

class ExpenseEntryFragment : Fragment() {
    private lateinit var expense: Expense
    private var receivedTime: Long? = null
    private var amountType: Int? = null
    var title: String? = null

    private lateinit var expenseEntryViewModel: ExpenseEntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expense_entry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        expenseEntryViewModel = ViewModelProvider(this).get(ExpenseEntryViewModel::class.java)
        amountType = ExpenseEntryFragmentArgs.fromBundle(requireArguments()).amountType


        view.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                /*Toast.makeText(
                    requireContext(),
                    adapterView?.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT
                ).show()*/
                title = adapterView?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        view.expense_entry_date.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {

                expenseEntryViewModel.persianDatePicker(requireContext(), view)

            }
        }
        expenseEntryViewModel.longTime.observe(viewLifecycleOwner) {

            receivedTime = it

        }

        button.setOnClickListener {

            val amount = expense_entry_amount.text.toString()
            val date = expense_entry_date.text.toString()
            val description = expense_entry_description.text.toString()
            if (amount.isNotEmpty() && date.isNotEmpty() && description.isNotEmpty()) {
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
        val newTitle = this.title
        val amount = view?.expense_entry_amount?.text.toString().toLong()
        val date = this.receivedTime
        val description = view?.expense_entry_description?.text.toString()
        val amountType = this.amountType
        expense = Expense(0,newTitle ?: "", amount, date ?: 0, description, amountType ?: 2)
        expenseEntryViewModel.insertExpense(expense)

    }


}

