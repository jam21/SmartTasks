package com.smarttasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.smarttasks.domain.entities.Task
import com.smarttasks.ui.tasks.TaskItem
import com.smarttasks.ui.tasks.TaskScreen
import com.smarttasks.ui.tasks.TaskViewModel
import com.smarttasks.ui.theme.SmartTasksTheme
import com.smarttasks.ui.theme.smallPadding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartTasksTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.fillMaxSize().padding(innerPadding).smallPadding()) {
                        val viewModel:TaskViewModel = hiltViewModel()
                        LaunchedEffect(Unit){
                            viewModel.getTasks()
                        }
                        TaskScreen(modifier = Modifier.fillMaxSize(),response = viewModel.data.collectAsState())
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