package com.smarttasks.domain.repositories

import com.smarttasks.domain.entities.Task

interface TaskRepository {
    suspend fun getTasks(): List<Task>
}
