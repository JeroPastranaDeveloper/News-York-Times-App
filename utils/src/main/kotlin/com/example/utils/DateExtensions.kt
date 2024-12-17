package com.example.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val PATTERN_DATE = "dd/MM/yyyy"
const val API_PATTERN_DATE = "yyyy-MM-dd'T'HH:mm:ssZ"

fun String.formatToDatePattern(): String = try {
    val simpleDateFormat = SimpleDateFormat(PATTERN_DATE, Locale.getDefault())
    val date = this.getDate()
    date?.let { simpleDateFormat.format(it) }.orEmpty()
} catch (ignored: Exception) {
    ignored.printStackTrace()
    emptyString()
}

fun String.getDate(): Date? {
    val formatter = SimpleDateFormat(API_PATTERN_DATE, Locale.getDefault())
    return try {
        formatter.parse(this)
    } catch (ignored: ParseException) {
        ignored.printStackTrace()
        null
    }
}