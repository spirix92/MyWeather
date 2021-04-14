package com.selen.myweather.helper

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Pyaterko Aleksey
 */
class DateParser {

    private val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }

    private val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }

    private val incomingDateFormat =
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }

    fun getDate(textDate: String): String {
        val date = incomingTextDateToDate(textDate)

        return date?.let {
            dateFormat.format(date)
        } ?: "null"
    }

    fun getTime(textDate: String): String {
        val date = incomingTextDateToDate(textDate)

        return date?.let {
            timeFormat.format(date)
        } ?: "null"
    }

    private fun incomingTextDateToDate(date: String): Date? {
        return try {
            incomingDateFormat.parse(date)
        } catch (e: ParseException) {
            null
        }
    }

}