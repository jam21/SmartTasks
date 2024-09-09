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
import com.smarttasks.data.mappers.TaskData
import com.smarttasks.domain.entities.Task
import com.smarttasks.ui.tasks.TaskItem
import com.smarttasks.ui.theme.SmartTasksTheme
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
                Column(Modifier.fillMaxWidth()) {
                    Text(text = "AOA M Younas", color = MaterialTheme.colorScheme.secondary)
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        TaskItem(Modifier.padding(innerPadding), task = Task("id", Date(),Date(),"AOA Muhammad Younas","",0))
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SmartTasksTheme {
        Greeting("Android")
    }
}