package com.smarttasks.ui.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smarttasks.domain.entities.Task
import java.util.Date

@Composable
fun TaskItem(modifier: Modifier = Modifier, onClick: (() -> Unit)={}, task: Task) =
    Card(modifier = modifier.fillMaxWidth(), onClick = onClick, shape = MaterialTheme.shapes.medium) {
        Column(Modifier.padding(8.dp)) {
            Text(text = task.title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onSurface)
            HorizontalDivider()
            Text(task.description)
            Text(task.dueDate.toString())
            Text(task.priority.toString())
            Text(task.targetDate.toString())
        }
    }

@Preview(showBackground = true)
@Composable
fun TaskItemPreview() {
    TaskItem(onClick = {}, task = Task("id", Date(), Date(), "Title", "Description", 0))
}