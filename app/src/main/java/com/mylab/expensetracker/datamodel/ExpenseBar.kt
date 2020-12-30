package com.mylab.expensetracker.datamodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ExpenseBar(

    var expenseBarList:List<Expense>

):Parcelable