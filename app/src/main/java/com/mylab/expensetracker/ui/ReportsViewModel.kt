package com.mylab.expensetracker.ui

import android.app.Application
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mylab.expensetracker.R
import com.mylab.expensetracker.datamodel.Expense
import com.mylab.expensetracker.db.ExpenseDataBase
import com.mylab.expensetracker.db.ExpenseRepository
import com.mylab.expensetracker.util.Constants
import ir.hamsaa.persiandatepicker.Listener
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import kotlinx.android.synthetic.main.fragment_reports.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.ArrayList

class ReportsViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: ExpenseRepository
    var list = MutableLiveData<List<Expense>>()
    var incomeList = MutableLiveData<List<Expense>>()

    var incomeExpense = MutableLiveData<ArrayList<Long>>()


    init {
        val expenseDao = ExpenseDataBase.getDataBase(application).expenseDao()
        repository = ExpenseRepository(expenseDao)
    }

    fun incomeExpenseSumQuery(startDate: Long, endDate: Long) {

        viewModelScope.launch(Dispatchers.IO) {

            val income = repository.incomeSumQuery(startDate, endDate)
            val expense = repository.expenseSumQuery(startDate, endDate)
            val incExp = ArrayList<Long>()
            incExp.add(income)
            incExp.add(expense)
            incomeExpense.postValue(incExp)


        }

    }


    private var _longTime1 = MutableLiveData<Long>()
    val longTime1: LiveData<Long>
        get() = _longTime1

    private var _longTime2 = MutableLiveData<Long>()
    val longTime2: LiveData<Long>
        get() = _longTime2

    fun dateRangeQuery(startDate: Long, endDate: Long) {

        viewModelScope.launch(Dispatchers.IO) {

            list.postValue(repository.dateRangeQuery(startDate, endDate))

        }
    }

    fun incomeDateRangeQuery(startDate: Long, endDate: Long) {
        viewModelScope.launch(Dispatchers.IO) {

            incomeList.postValue(repository.incomeDateRangeQuery(startDate, endDate))

        }

    }


    fun persianDatePicker1(context: Context, view: View) {
        val initDate = PersianCalendar()
        initDate.setPersianDate(1399, 1, 1)
        val typeface = ResourcesCompat.getFont(context, R.font.f_mitra)
        val picker = PersianDatePickerDialog(context)
            .setPositiveButtonString("تایید")
            .setNegativeButton("انصراف")
            .setTodayButton("امروز")
            .setTodayButtonVisible(true)
            .setMinYear(1300)
            .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
            .setInitDate(initDate)
            .setTypeFace(typeface)
            .setActionTextColor(Color.GRAY)
            .setActionTextColor(Color.BLACK)
            .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
            .setShowInBottomSheet(true)
            .setListener(object : Listener {
                override fun onDateSelected(persianCalendar: PersianCalendar) {
                    /*Log.d(
                        Constants.TAG,
                        "onDateSelected: " + persianCalendar.gregorianChange
                    )*/ //Fri Oct 15 03:25:44 GMT+04:30 1582
                    //Log.d(Constants.TAG, "onDateSelected: " + persianCalendar.timeInMillis) //1583253636577
                    _longTime1.value = persianCalendar.timeInMillis

                    /*Log.d(
                        Constants.TAG,
                        "onDateSelected: " + persianCalendar.time
                    )*/ //Tue Mar 03 20:10:36 GMT+03:30 2020
                    /*Log.d(Constants.TAG, "onDateSelected: " + persianCalendar.delimiter) //  /
                    Log.d(
                        Constants.TAG,
                        "onDateSelected: " + persianCalendar.persianLongDate
                    )*/ // سه‌شنبه  13  اسفند  1398
                    /*Log.d(
                        Constants.TAG,
                        "onDateSelected: " + persianCalendar.persianLongDateAndTime
                    )*/ //سه‌شنبه  13  اسفند  1398 ساعت 20:10:36
                    /*Log.d(
                        Constants.TAG,
                        "onDateSelected: " + persianCalendar.persianMonthName
                    )*/ //اسفند
                    /*Log.d(
                        Constants.TAG,
                        "onDateSelected: " + persianCalendar.isPersianLeapYear
                    )*/ //false
                    /*Toast.makeText(
                        context,
                        persianCalendar.persianYear.toString() + "/" + persianCalendar.persianMonth + "/" + persianCalendar.persianDay,
                        Toast.LENGTH_LONG
                    ).show()*/
                    view.edit_text_reports_from_date.setText("${persianCalendar.persianYear}/${persianCalendar.persianMonth}/${persianCalendar.persianDay}")


                }

                override fun onDismissed() {}
            })

        picker.show()

    }

    fun persianDatePicker2(context: Context, view: View) {
        val initDate = PersianCalendar()
        initDate.setPersianDate(1399, 1, 1)
        val typeface = ResourcesCompat.getFont(context, R.font.f_mitra)
        val picker = PersianDatePickerDialog(context)
            .setPositiveButtonString("تایید")
            .setNegativeButton("انصراف")
            .setTodayButton("امروز")
            .setTodayButtonVisible(true)
            .setMinYear(1300)
            .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
            .setInitDate(initDate)
            .setTypeFace(typeface)
            .setActionTextColor(Color.GRAY)
            .setActionTextColor(Color.BLACK)
            .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
            .setShowInBottomSheet(true)
            .setListener(object : Listener {
                override fun onDateSelected(persianCalendar: PersianCalendar) {
                    /*Log.d(
                        Constants.TAG,
                        "onDateSelected: " + persianCalendar.gregorianChange
                    )*/ //Fri Oct 15 03:25:44 GMT+04:30 1582
                    //Log.d(Constants.TAG, "onDateSelected: " + persianCalendar.timeInMillis) //1583253636577
                    _longTime2.value = persianCalendar.timeInMillis

                    /*Log.d(
                        Constants.TAG,
                        "onDateSelected: " + persianCalendar.time
                    )*/ //Tue Mar 03 20:10:36 GMT+03:30 2020
                    /*Log.d(Constants.TAG, "onDateSelected: " + persianCalendar.delimiter) //  /
                    Log.d(
                        Constants.TAG,
                        "onDateSelected: " + persianCalendar.persianLongDate
                    )*/ // سه‌شنبه  13  اسفند  1398
                    /*Log.d(
                        Constants.TAG,
                        "onDateSelected: " + persianCalendar.persianLongDateAndTime
                    )*/ //سه‌شنبه  13  اسفند  1398 ساعت 20:10:36
                    /*Log.d(
                        Constants.TAG,
                        "onDateSelected: " + persianCalendar.persianMonthName
                    )*/ //اسفند
                    /*Log.d(
                        Constants.TAG,
                        "onDateSelected: " + persianCalendar.isPersianLeapYear
                    )*/ //false
                    /*Toast.makeText(
                        context,
                        persianCalendar.persianYear.toString() + "/" + persianCalendar.persianMonth + "/" + persianCalendar.persianDay,
                        Toast.LENGTH_LONG
                    ).show()*/
                    view.edit_text_reports_to_date.setText("${persianCalendar.persianYear}/${persianCalendar.persianMonth}/${persianCalendar.persianDay}")


                }

                override fun onDismissed() {}
            })

        picker.show()

    }
}