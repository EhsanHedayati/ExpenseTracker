package com.mylab.expensetracker.ui

import android.os.Bundle
import android.util.Log
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
import com.mylab.expensetracker.datamodel.ExpenseBar
import kotlinx.android.synthetic.main.fragment_bar_chart.*
import kotlinx.android.synthetic.main.fragment_bar_chart2.*


class BarChartFragment2 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bar_chart2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val incomeList = BarChartFragment2Args.fromBundle(requireArguments()).incomeBarList
        Log.i("MyTag", incomeList.toString())

        getChart(incomeList)

    }

    private fun getChart(incomeList: ExpenseBar) {
        val pieEntries = ArrayList<PieEntry>()

        incomeList.expenseBarList.forEach {
            pieEntries.add(PieEntry(it.amount.toFloat(), it.title))
        }



        val pieDataSet = PieDataSet(pieEntries, "نمودار درآمدها")
        pieDataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()

        val pieData = PieData(pieDataSet)
        pieData.setValueTextSize(10f)
        pi_chart_income.animateXY(5000, 5000)
        pi_chart_income.data = pieData
        pi_chart_income.isDrawHoleEnabled = false

        val description = Description()
        description.text = "درآمدها در بازه زمانی"
        pi_chart_income.description = description

        pi_chart_income.invalidate()
    }
}