package com.mylab.expensetracker.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mylab.expensetracker.R
import com.mylab.expensetracker.datamodel.Expense
import com.mylab.expensetracker.datamodel.ExpenseBar
import com.mylab.expensetracker.datamodel.ExpenseIncomeBar
import kotlinx.android.synthetic.main.fragment_reports.*
import kotlinx.android.synthetic.main.fragment_reports.view.*


class ReportsFragment : Fragment() {

    private var startDate: Long? = null
    private var endDate: Long? = null
    private lateinit var reportsViewModel: ReportsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reports, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reportsViewModel = ViewModelProvider(this).get(ReportsViewModel::class.java)



        view.edit_text_reports_from_date.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {

                    reportsViewModel.persianDatePicker1(requireContext(), view)

                }
            }

        view.edit_text_reports_to_date.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {

                    reportsViewModel.persianDatePicker2(requireContext(), view)

                }
            }

        reportsViewModel.longTime1.observe(viewLifecycleOwner) {
            startDate = it


        }

        reportsViewModel.longTime2.observe(viewLifecycleOwner) {
            endDate = it

        }

        button_query_1.setOnClickListener {

            if (view?.edit_text_reports_from_date?.text.isNullOrEmpty()
                && view?.edit_text_reports_to_date?.text.isNullOrEmpty()
            ) {

                Toast.makeText(
                    requireContext(),
                    "تاریخ شروع و پایان مشخص گردد",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                reportsViewModel.incomeDateRangeQuery(startDate ?: 0, endDate ?: 0)
                reportsViewModel.incomeList.observe(viewLifecycleOwner) {
                    if (it.isNotEmpty()) {
                        val mExpenseBar = ExpenseBar(it)
                        findNavController().navigate(
                            ReportsFragmentDirections.actionReportsFragmentToBarChartFragment2(
                                mExpenseBar
                            )
                        )
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "هیچ مقادیری برای بازه مورد نظر وجود ندارد",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }

            }


        }



        button_query_2.setOnClickListener {

            getReportIncomeExpense(view)


        }

        button_query.setOnClickListener {

            getReport()

        }


    }

    private fun getReportIncomeExpense(view: View) {
        if (view?.edit_text_reports_from_date?.text.isNullOrEmpty()
            && view?.edit_text_reports_to_date?.text.isNullOrEmpty()
        ) {

            Toast.makeText(
                requireContext(),
                "تاریخ شروع و پایان مشخص گردد",
                Toast.LENGTH_SHORT
            ).show()

        } else {
            reportsViewModel.incomeExpenseSumQuery(startDate ?: 0, endDate ?: 0)
            reportsViewModel.incomeExpense.observe(viewLifecycleOwner) {

                if (it.size > 0) {

                    val expenseIncome = ExpenseIncomeBar(it[1], it[0])
                    findNavController().navigate(
                        ReportsFragmentDirections.actionReportsFragmentToBarChartFragment1(
                            expenseIncome
                        )
                    )

                } else {
                    Toast.makeText(
                        requireContext(),
                        "هیچ مقادیری برای بازه مورد نظر وجود ندارد",
                        Toast.LENGTH_SHORT
                    ).show()

                }


            }


        }
    }


    private fun getReport() {

        if (view?.edit_text_reports_from_date?.text.isNullOrEmpty()
            && view?.edit_text_reports_to_date?.text.isNullOrEmpty()
        ) {
            Toast.makeText(
                requireContext(),
                "تاریخ شروع و پایان مشخص گردد",
                Toast.LENGTH_SHORT
            ).show()

        } else {
            reportsViewModel.dateRangeQuery(startDate ?: 0, endDate ?: 0)
            reportsViewModel.list.observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    val myExpenseBar = ExpenseBar(it)
                    findNavController().navigate(
                        ReportsFragmentDirections.actionReportsFragmentToBarChartFragment(
                            myExpenseBar
                        )
                    )

                } else {
                    Toast.makeText(
                        requireContext(),
                        "هیچ مقادیری برای بازه مورد نظر وجود ندارد",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }

        }

    }

}




