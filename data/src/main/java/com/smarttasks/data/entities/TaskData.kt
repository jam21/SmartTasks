package com.smarttasks.data.entities

import com.google.gson.annotations.SerializedName
import java.util.Date

class TaskData(
    @SerializedName("id")
    val id: String,
    @SerializedName("TargetDate")
    val targetDate: Date,
    @SerializedName("DueDate")
    val dueDate: Date?,
    @SerializedName("Title")
    val title: String,
    @SerializedName("Description")
    val description: String,
    @SerializedName("Priority")
    val priority: Int){
    override fun toString(): String {
        return "$id $targetDate $dueDate $title $description $priority"
    }
}