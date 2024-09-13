package com.smarttasks.data.mappers

import android.util.Log
import com.smarttasks.data.entities.TaskData
import com.smarttasks.domain.entities.Task


object TaskMapper {
    //    fun fromTask(task:Task): TaskData {
//        return TaskData(task.id)
//    }
    fun toTask(taskData: TaskData): Task {
        Log.e("Task","Data: $taskData")
        return Task(
            taskData.id,
            taskData.targetDate,
            taskData.dueDate,
            taskData.title,
            taskData.description,
            taskData.priority
        )

    }
}