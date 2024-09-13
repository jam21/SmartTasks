package com.smarttasks.data.datasources

import com.smarttasks.domain.entities.Task


interface TaskDataSource {
    suspend fun getTasks(): List<Task>
}