package com.smarttasks.data.entities

import com.google.gson.annotations.SerializedName

data class TaskResponse(@SerializedName("tasks") val tasks: List<TaskData>)