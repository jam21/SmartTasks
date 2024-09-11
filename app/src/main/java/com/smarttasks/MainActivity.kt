package com.smarttasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smarttasks.data.mappers.TaskData
import com.smarttasks.domain.entities.Task
import com.smarttasks.ui.tasks.TaskItem
import com.smarttasks.ui.theme.SmartTasksTheme
import com.smarttasks.ui.theme.mediumPadding
import com.smarttasks.ui.theme.smallPadding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var myTaskData: TaskData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartTasksTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding).smallPadding()) {
                        TaskItem(Modifier.padding(4.dp), task = Task("id", Date(),Date(),"AOA Muhammad Younas","",0))
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SmartTasksTheme {
        TaskItem(Modifier.padding(4.dp), task = Task("id", Date(),Date(),"AOA Muhammad Younas","",0))
    }
}