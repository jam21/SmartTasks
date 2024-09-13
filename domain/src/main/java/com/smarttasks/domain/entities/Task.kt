package com.smarttasks.domain.entities

import java.util.Date

data class Task(
    val id: String,
    val targetDate: Date,
    val dueDate: Date?,
    val title: String,
    val description: String,
    val priority: Int
)
