package com.mylab.expensetracker.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.mylab.expensetracker.R
import com.mylab.expensetracker.datamodel.ExpenseIncomeBar
import kotlinx.android.synthetic.main.fragment_bar_chart1.*


class BarChartFragment1 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bar_chart1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val expInc = BarChartFragment1Args.fromBundle(requireArguments()).expenseincomebar
        getExpenseIncomeChart(expInc)


    }

    private fun getExpenseIncomeChart(expInc: ExpenseIncomeBar) {
        val pieEntries = ArrayList<PieEntry>()
        pieEntries.add(PieEntry(expInc.expense.toFloat(), "هزینه ها"))
        pieEntries.add(PieEntry(expInc.income.toFloat(), "درآمدها"))

        pi_chart_expInc.animateXY(5000, 5000)
        val pieDataSet = PieDataSet(pieEntries, "هزینه ها درآمدها")
        pieDataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()

        val pieData = PieData(pieDataSet)
        pieData.setValueTextSize(10f)
        pi_chart_expInc.data = pieData

        val description = Description()
        description.text = "مقایسه هزینه ها و درآمدها"
        pi_chart_expInc.description = description

        pi_chart_expInc.invalidate()
    }
}