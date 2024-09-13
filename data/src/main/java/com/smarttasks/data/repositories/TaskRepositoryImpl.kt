package com.smarttasks.data.repositories

import com.smarttasks.data.datasources.TaskDataSource
import com.smarttasks.domain.entities.Task
import com.smarttasks.domain.repositories.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val dataSource: TaskDataSource): TaskRepository {
    override suspend fun getTasks(): List<Task> {
        return dataSource.getTasks()
    }
}