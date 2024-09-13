package com.smarttasks.data.datasources

import com.smarttasks.data.mappers.TaskMapper
import com.smarttasks.data.network.Endpoints
import com.smarttasks.domain.entities.Task

import javax.inject.Inject

class TaskRemoteDataSource @Inject constructor(private val endpoints: Endpoints) : TaskDataSource {
    override suspend fun getTasks(): List<Task> {
        return endpoints.getTasks().tasks.map { TaskMapper.toTask(it) }
    }

}