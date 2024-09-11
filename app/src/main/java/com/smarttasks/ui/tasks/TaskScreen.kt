package com.smarttasks.ui.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.smarttasks.R
import com.smarttasks.domain.entities.Task
import com.smarttasks.ui.theme.HorizontalMediumSpace
import com.smarttasks.ui.theme.HorizontalSmallSpace
import com.smarttasks.ui.theme.mediumPadding
import com.smarttasks.utils.daysLeft
import com.smarttasks.utils.toMMMDdYyyy
import java.util.Date

@Composable
fun TaskItem(modifier: Modifier = Modifier, task: Task, onClick: (() -> Unit) = {}) =
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        shape = MaterialTheme.shapes.extraSmall
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .mediumPadding()
        ) {
            Text(
                text = task.title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            HorizontalSmallSpace(Modifier.fillMaxWidth())
            HorizontalDivider(color = MaterialTheme.colorScheme.tertiary)
            HorizontalMediumSpace(Modifier.fillMaxWidth())
            Row(Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(2f), horizontalAlignment = Alignment.Start) {
                    Text(
                        text = stringResource(R.string.due_date),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                    HorizontalSmallSpace(Modifier.fillMaxWidth())
                    Text(
                        task.dueDate.toMMMDdYyyy(),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                    Text(
                        text = stringResource(R.string.days_left),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                    HorizontalSmallSpace(Modifier.fillMaxWidth())
                    Text(
                        task.daysLeft().toString(),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

        }
    }

@Composable
fun TaskList(modifier: Modifier = Modifier, tasks: List<Task>) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(tasks, key = { item -> item.id }) {item->
            HorizontalMediumSpace(Modifier.fillMaxWidth())
            TaskItem(modifier = modifier, task=item)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskItemPreview() {
    TaskItem(onClick = {}, task = Task("id", Date(), Date(), "Title", "Description", 0))
}

@Preview(showBackground = true)
@Composable
fun TaskListPreview() {
    TaskList(tasks = listOf(Task("id", Date(), Date(), "Title", "Description", 0)))
}