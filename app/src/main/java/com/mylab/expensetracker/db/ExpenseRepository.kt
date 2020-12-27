package com.mylab.expensetracker.db

import androidx.lifecycle.LiveData
import com.mylab.expensetracker.datamodel.Expense

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


}