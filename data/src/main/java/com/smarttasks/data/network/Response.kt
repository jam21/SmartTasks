package com.smarttasks.data.network

sealed class Response<out T>(data: T? = null, message: String? = null){
    data object LOADING : Response<Nothing>()
    data class SUCCESS<out T>(val data: T) : Response<T>()
    data class ERROR(val message: String) : Response<Nothing>()
}

