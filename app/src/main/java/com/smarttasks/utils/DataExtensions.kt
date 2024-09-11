package com.smarttasks.utils

import com.smarttasks.domain.entities.Task
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


const val format_MMM_dd_yyyy = "MMM dd yyyy"
val simpleDateFormat_MMM_dd_yyyy: DateFormat =
    SimpleDateFormat(format_MMM_dd_yyyy, Locale.getDefault())

fun Date.toMMMDdYyyy(): String = simpleDateFormat_MMM_dd_yyyy.format(this)

fun Task.daysLeft(): Int {
    val diff = this.dueDate.time - Date().time
    return (diff / (1000 * 60 * 60 * 24)).toInt()
}