package com.mylab.expensetracker.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.mylab.expensetracker.R
import com.mylab.expensetracker.datamodel.ExpenseBar
import kotlinx.android.synthetic.main.fragment_bar_chart.*


class BarChartFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bar_chart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val expenseList = BarChartFragmentArgs.fromBundle(requireArguments()).expenseBarList

        getPieChart(expenseList)


    }

    private fun getPieChart(expenseList: ExpenseBar) {
        val pieEntries = ArrayList<PieEntry>()

        expenseList.expenseBarList.forEach {
            pieEntries.add(PieEntry(it.amount.toFloat(), it.title))
        }

        pi_chart.animateXY(5000, 5000)
        val pieDataSet = PieDataSet(pieEntries, "نمودار هزینه مصرفی")
        pieDataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()

        val pieData = PieData(pieDataSet)
        pieData.setValueTextSize(10f)
        pi_chart.data = pieData

        val description = Description()
        description.text = "هزینه مصرفی در بازه زمانی"
        pi_chart.description = description

        pi_chart.invalidate()
    }
}