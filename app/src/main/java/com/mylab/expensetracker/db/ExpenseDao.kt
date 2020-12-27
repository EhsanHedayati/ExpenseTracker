package com.mylab.expensetracker.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mylab.expensetracker.datamodel.Expense


@Dao
interface ExpenseDao {

    @Insert
    suspend fun insertExpense(expense: Expense)

    @Update
    suspend fun updateExpense(expense: Expense)

    @Query("SELECT * FROM expense_table ORDER BY date DESC")
    fun readAllExpense(): LiveData<List<Expense>>

    @Delete
    suspend fun deleteExpense(expense: Expense)

    @Query("DELETE FROM expense_table")
    suspend fun deleteAllExpense()







}