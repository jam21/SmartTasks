package com.smarttasks.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smarttasks.data.network.Endpoints
import com.smarttasks.data.network.Response
import com.smarttasks.domain.entities.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


const val format_MMM_dd_yyyy = "MMM dd yyyy"
val simpleDateFormat_MMM_dd_yyyy: DateFormat =
    SimpleDateFormat(format_MMM_dd_yyyy, Locale.getDefault())

fun Date?.toMMMDdYyyy(): String? = this?.let { simpleDateFormat_MMM_dd_yyyy.format(it) }

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