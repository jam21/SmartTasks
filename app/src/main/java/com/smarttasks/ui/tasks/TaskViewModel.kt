package com.smarttasks.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smarttasks.data.network.Response
import com.smarttasks.domain.entities.Task
import com.smarttasks.domain.repositories.TaskRepository
import com.smarttasks.domain.usecases.GetTasksUseCase
import com.smarttasks.utils.flowApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val taskRepository: TaskRepository, private val getTasksUseCase: GetTasksUseCase):ViewModel() {
    private val _data = MutableStateFlow<Response<List<Task>>>(Response.LOADING)
    val data: StateFlow<Response<List<Task>>> = _data
    fun getTasks() {
        viewModelScope.launch {
            flowApi { getTasksUseCase(taskRepository) }.flowOn(Dispatchers.IO).collectLatest {
                _data.value = it
            }
        }
    }
}