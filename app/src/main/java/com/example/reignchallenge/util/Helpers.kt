package com.example.reignchallenge.util

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

object Helpers{
    val TAG = javaClass.simpleName

    @JvmStatic
    fun differenceWithCurrentDate(dateAfterString: String): String{
        val dateAfter: Date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.US).parse(dateAfterString)
        val dateBefore: Date = Calendar.getInstance().time

        val diff = dateBefore.time - dateAfter.time

        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24

        var dateString = ""
        val isYesterday = DateUtils.isToday(dateAfter.time + DateUtils.DAY_IN_MILLIS)

        if (isYesterday)
            dateString = "Yesterday"
        else if (days > 0L && !isYesterday)
            dateString = "$days d"
        else if (hours > 0L && days == 0L)
            dateString = "$hours h"
        else if (minutes > 0L && hours == 0L)
            dateString = "$minutes m"

        return dateString
    }
}