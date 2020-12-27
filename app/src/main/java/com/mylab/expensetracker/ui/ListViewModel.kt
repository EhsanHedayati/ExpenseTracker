package com.mylab.expensetracker.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mylab.expensetracker.datamodel.Expense
import com.mylab.expensetracker.db.ExpenseDataBase
import com.mylab.expensetracker.db.ExpenseRepository

class ListViewModel(application: Application) : AndroidViewModel(application) {

    val readAllExpense: LiveData<List<Expense>>

    init {
        val expenseDao = ExpenseDataBase.getDataBase(application).expenseDao()
        val repository = ExpenseRepository(expenseDao)
        readAllExpense = repository.readAllExpense
    }


}