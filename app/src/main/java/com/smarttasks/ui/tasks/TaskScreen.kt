package com.smarttasks.ui.tasks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.smarttasks.R
import com.smarttasks.data.network.Response
import com.smarttasks.domain.entities.Task
import com.smarttasks.ui.theme.HorizontalMediumSpace
import com.smarttasks.ui.theme.HorizontalSmallSpace
import com.smarttasks.ui.theme.extraLargeWidth
import com.smarttasks.ui.theme.mediumPadding
import com.smarttasks.ui.theme.mediumSize
import com.smarttasks.utils.daysLeft
import com.smarttasks.utils.isToday
import com.smarttasks.utils.toMMMDdYyyy
import kotlinx.coroutines.launch
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
                        task.dueDate?.toMMMDdYyyy() ?: "No Limit",
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
        items(tasks, key = { item -> item.id }) { item ->
            HorizontalMediumSpace(Modifier.fillMaxWidth())
            TaskItem(modifier = modifier, task = item)
        }
    }
}

@Composable
fun TaskScreen(modifier: Modifier = Modifier, response: State<Response<List<Task>>>) {
    when (response.value) {
        is Response.ERROR -> Text(text = "Error: ${(response.value as Response.ERROR).message}")
        Response.LOADING -> Box(modifier, Alignment.Center) {
            CircularProgressIndicator(
                modifier = Modifier.extraLargeWidth(),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }

        is Response.SUCCESS -> TaskViewpager(
            modifier.fillMaxWidth(),
            (response.value as Response.SUCCESS<List<Task>>).data.sortedWith(
                compareBy<Task> { it.targetDate } // Sort by date first
                    .thenByDescending { it.priority } // Then sort by priority in descending order
            ).groupBy { it.targetDate })
    }
}

@Composable
fun TaskViewpager(modifier: Modifier = Modifier, data: Map<Date, List<Task>>) {
    val size = data.size
    if (size == 0) return

    val pagerState = rememberPagerState(pageCount = {
        data.size
    })
    val coroutineScope = rememberCoroutineScope()
    var currentDat by remember { mutableStateOf(data.keys.toList()[0]) }
    val currentTitle by remember(currentDat) {

        mutableStateOf(if (currentDat.isToday()) "Today" else currentDat.toMMMDdYyyy() ?: "")
    }

    HorizontalMediumSpace(Modifier.fillMaxWidth())
    TitleText(currentTitle, moveNext = {
        coroutineScope.launch {
            if (pagerState.currentPage == size - 1) return@launch
            pagerState.animateScrollToPage(pagerState.currentPage + 1)
        }

    }, movePrevious = {
        coroutineScope.launch {
            if (pagerState.currentPage == 0) return@launch
            pagerState.animateScrollToPage(pagerState.currentPage - 1)
        }
    })

    HorizontalMediumSpace(Modifier.fillMaxWidth())

    HorizontalPager(pagerState, modifier = modifier, key = { it }) { page ->
        if (pagerState.currentPageOffsetFraction == 0f) {
            currentDat = data.keys.toList()[page]
        }

        val tasks = data[currentDat]
        if (tasks != null) {
            TaskList(modifier = modifier, tasks = tasks)
        }

    }
}

@Composable
private fun TitleText(title: String, moveNext: () -> Unit = {}, movePrevious: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = null,
            modifier = Modifier
                .size(mediumSize())
                .clickable {
                    movePrevious()
                },
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier
                .size(mediumSize())
                .clickable {
                    moveNext()
                }
        )
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
//    TaskResponse(tasks = listOf(Task("id", Date(), Date(), "Title", "Description", 0)))
}