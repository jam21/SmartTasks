package com.smarttasks.data.network

import com.smarttasks.data.entities.TaskResponse
import retrofit2.http.GET

interface Endpoints {
    @GET("/")
    suspend fun getTasks(): TaskResponse
}
