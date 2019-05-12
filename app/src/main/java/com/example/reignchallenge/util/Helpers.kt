package com.example.reignchallenge.util

import java.text.SimpleDateFormat
import java.util.*

object Helpers{

    @JvmStatic
    fun convertDate(date: String): String{
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        format.timeZone = TimeZone.getTimeZone("UTC")
        val parse = format.parse(date)

        val formatResult = SimpleDateFormat("dd/MM/yyy HH:mm", Locale.US)
        return formatResult.format(parse)
    }
}