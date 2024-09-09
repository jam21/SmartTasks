package com.smarttasks.data.mappers

import com.smarttasks.domain.entities.Task

data class TaskData(val id:String)
object TaskMapper{
    fun fromTask(task:Task):TaskData{
        return TaskData(task.id)
    }
    fun toTask(task:TaskData):TaskData{
        return task

    }
}