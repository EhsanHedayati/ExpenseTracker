package com.mylab.expensetracker.db

import android.content.Context
import androidx.lifecycle.LiveData
import com.mylab.expensetracker.datamodel.Expense
import kotlin.properties.Delegates

class ExpenseRepository(private val expenseDao: ExpenseDao) {


    val readAllExpense: LiveData<List<Expense>> = expenseDao.readAllExpense()


    suspend fun insertExpense(expense: Expense) {
        expenseDao.insertExpense(expense)

    }

    suspend fun updateExpense(expense: Expense) {
        expenseDao.updateExpense(expense)

    }

    suspend fun deleteExpense(expense: Expense) {
        expenseDao.deleteExpense(expense)

    }

    suspend fun deleteAllExpense() {
        expenseDao.deleteAllExpense()

    }


    suspend fun dateRangeQuery(startDate: Long, endDate: Long): List<Expense> {
        return expenseDao.expenseDateRangeQuery(startDate, endDate)

    }

    suspend fun incomeSumQuery(startDate: Long, endDate: Long): Long {
        return expenseDao.incomeSumQuery(startDate, endDate)
    }

    suspend fun expenseSumQuery(startDate: Long, endDate: Long): Long {
        return expenseDao.expenseSumQuery(startDate, endDate)
    }


}