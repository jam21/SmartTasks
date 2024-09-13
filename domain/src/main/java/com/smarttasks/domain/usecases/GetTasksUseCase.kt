package com.smarttasks.domain.usecases

import com.smarttasks.domain.entities.Task
import com.smarttasks.domain.repositories.TaskRepository

class GetTasksUseCase {
    suspend operator fun invoke(taskRepository: TaskRepository): List<Task> {
        return taskRepository.getTasks()
    }
}