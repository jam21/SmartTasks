package com.smarttasks.utils

import androidx.lifecycle.ViewModel
import com.smarttasks.data.network.Response
import com.smarttasks.domain.entities.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


const val format_MMM_dd_yyyy = "MMM dd yyyy"
val simpleDateFormat_MMM_dd_yyyy: DateFormat =
    SimpleDateFormat(format_MMM_dd_yyyy, Locale.getDefault())

fun Date?.toMMMDdYyyy(): String? = this?.let { simpleDateFormat_MMM_dd_yyyy.format(it) }
fun Date.isToday(): Boolean {
    // Create Calendar instance for the given date
    val calendarGiven = Calendar.getInstance().apply {
        time = this@isToday
        // Set time components to zero to focus on date-only comparison
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    // Create Calendar instance for the current date
    val calendarCurrent = Calendar.getInstance().apply {
        // Set time components to zero to focus on date-only comparison
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    // Compare the dates (ignoring the time)
    return calendarGiven.get(Calendar.YEAR) == calendarCurrent.get(Calendar.YEAR) &&
            calendarGiven.get(Calendar.MONTH) == calendarCurrent.get(Calendar.MONTH) &&
            calendarGiven.get(Calendar.DAY_OF_MONTH) == calendarCurrent.get(Calendar.DAY_OF_MONTH)
}



fun Task.daysLeft(): Int {
    val dueDate = this.dueDate ?: return Int.MAX_VALUE
    val diff = dueDate.time - Date().time
    return (diff / (1000 * 60 * 60 * 24)).toInt()
}

fun <T> ViewModel.flowApi(call: suspend () -> T): Flow<Response<T>> = flow {

    emit(Response.LOADING)
    val response = call()
    emit(Response.SUCCESS(response))
}.catch {
    emit(Response.ERROR(it.message ?: "Something went wrong"))
}