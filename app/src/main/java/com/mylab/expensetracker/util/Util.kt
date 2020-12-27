package com.mylab.expensetracker.util

import saman.zamani.persiandate.PersianDate
import saman.zamani.persiandate.PersianDateFormat


class Util {

    companion object {

        fun convertedTime(timeStamp: Long): String {

            val pDate = PersianDate(timeStamp)
            val pdFormatter = PersianDateFormat("Y/m/d")
            return pdFormatter.format(pDate)
        }

    }
}