package com.mylab.expensetracker.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mylab.expensetracker.R
import com.mylab.expensetracker.datamodel.Expense
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import kotlinx.android.synthetic.main.fragment_edit_delete.*
import kotlinx.android.synthetic.main.fragment_edit_delete.view.*
import kotlinx.android.synthetic.main.fragment_expense_entry.view.*
import kotlinx.android.synthetic.main.list_element.view.*


class EditDeleteFragment : Fragment() {
    lateinit var receivedExpense: Expense
    lateinit var editDeleteViewModel: EditDeleteViewModel
    private val persianCalendar = PersianCalendar()
    private var observedDate: Long? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_delete, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editDeleteViewModel = ViewModelProvider(this).get(EditDeleteViewModel::class.java)
        setHasOptionsMenu(true)


        receivedExpense = EditDeleteFragmentArgs.fromBundle(requireArguments()).expense
        if (receivedExpense.amountType == 1) {
            view.edit_delete_circleView.setImageResource(R.drawable.income)
        } else {
            view.edit_delete_circleView.setImageResource(R.drawable.expense)
        }

        view.edit_delete_title.setText(receivedExpense.title)
        view.edit_delete_amount.setText(receivedExpense.amount.toString())
        view.edit_delete_description.setText(receivedExpense.description)
        view.edit_delete_date.setText("${persianCalendar.persianYear}/${persianCalendar.persianMonth}/${persianCalendar.persianDay}")

        view.edit_delete_date.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {

                editDeleteViewModel.persianDatePicker(requireContext(), view)

            }
        }

        editDeleteViewModel.longTime.observe(viewLifecycleOwner) {
            observedDate = it
        }



        edit_delete_update.setOnClickListener {

            val title = view.edit_delete_title.text.toString()
            val amount = view.edit_delete_amount.text.toString().toLong()
            val date = observedDate
            val description = view.edit_delete_description.text
            val amountType = receivedExpense.amountType

            val updatedExpense = Expense(
                receivedExpense.id,
                title,
                amount,
                date ?: 0,
                description.toString(),
                amountType
            )
            if (view.edit_delete_title.text.isNotEmpty()
                && view.edit_delete_amount.text.isNotEmpty()
                && view.edit_delete_description.text.isNotEmpty()
                && view.edit_delete_description.text.isNotEmpty()
            ) {
                editDeleteViewModel.updateExpense(updatedExpense)

                Toast.makeText(requireContext(), "ویرایش با موفقیت انجام گردید", Toast.LENGTH_SHORT)
                    .show()
                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), "لطفا همه فیلدها تکمیل شود", Toast.LENGTH_SHORT)
                    .show()

            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {

            getDialog()

        }
        return super.onOptionsItemSelected(item)
    }

    private fun getDialog() {
        val builder = AlertDialog.Builder(activity)
        builder.setPositiveButton("بله", DialogInterface.OnClickListener { _, _ ->
            editDeleteViewModel.deleteExpense(receivedExpense)
            findNavController().navigateUp()
            Toast.makeText(activity, "حذف با موفقیت انجام شد.", Toast.LENGTH_SHORT).show()


        })
        builder.setNegativeButton("خیر", DialogInterface.OnClickListener { _, _ ->

        })
        builder.setTitle("حذف : ${receivedExpense.description}")
        builder.setMessage("برای حذف ${receivedExpense.description} مطمئن هستید؟")
        builder.create().show()
    }


}