package com.example.reignchallenge.util

import android.content.Context
import android.text.format.DateUtils
import android.util.Log
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import android.net.NetworkInfo
import androidx.core.content.ContextCompat.getSystemService
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService
import com.example.reignchallenge.model.dataBase.HitTable


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

    @JvmStatic
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    @JvmStatic
     fun hitsSubtitle(mHitsItem : HitTable): String{
         var subtitle = ""

         if (mHitsItem.author.isNotEmpty())
             subtitle = mHitsItem.author

         if (mHitsItem.createdAt.isNotEmpty())
             subtitle += " - " + Helpers.differenceWithCurrentDate(mHitsItem.createdAt)

         return subtitle
     }
}