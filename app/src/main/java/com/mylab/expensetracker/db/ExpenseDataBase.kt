package com.mylab.expensetracker.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mylab.expensetracker.datamodel.Expense


@Database(entities = [Expense::class], version = 1, exportSchema = false)
abstract class ExpenseDataBase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao

    companion object {

        @Volatile
        private var INSTANCE: ExpenseDataBase? = null

        fun getDataBase(context: Context): ExpenseDataBase {

            var tempInstance = INSTANCE
            if (tempInstance != null) {

                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    ExpenseDataBase::class.java,
                    "expense_database"
                ).build()
                INSTANCE = instance
                return instance

            }


        }


    }

}