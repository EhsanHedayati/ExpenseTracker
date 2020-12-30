package com.mylab.expensetracker.datamodel

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Parcelize
@Entity(tableName = "expense_table")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String,

    var amount: Long,
    var date: Long,
    var description: String,
    val amountType: Int
):Parcelable


