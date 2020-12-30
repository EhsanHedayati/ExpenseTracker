package com.mylab.expensetracker.datamodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ExpenseIncomeBar(
    var expense: Long,
    var income: Long
):Parcelable