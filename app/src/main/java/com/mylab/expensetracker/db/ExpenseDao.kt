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

    @Delete
    suspend fun deleteExpense(expense: Expense)

    @Query("SELECT * FROM expense_table ORDER BY date DESC")
    fun readAllExpense(): LiveData<List<Expense>>

    @Query("DELETE FROM expense_table")
    suspend fun deleteAllExpense()


    @Query("SELECT * FROM expense_table WHERE date BETWEEN :startDate AND :endDate AND amountType = 0")
    suspend fun expenseDateRangeQuery(startDate: Long, endDate: Long): List<Expense>

    @Query("SELECT SUM(amount) FROM expense_table WHERE date BETWEEN :startDate AND :endDate AND amountType = 1")
     suspend fun incomeSumQuery(startDate: Long, endDate: Long):Long

    @Query("SELECT SUM(amount) FROM expense_table WHERE date BETWEEN :startDate AND :endDate AND amountType = 0")
    suspend fun expenseSumQuery(startDate: Long, endDate: Long):Long











}