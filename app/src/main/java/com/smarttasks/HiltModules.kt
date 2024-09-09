package com.smarttasks

import com.smarttasks.data.mappers.TaskData
import com.smarttasks.domain.entities.Task
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object Task1Module {
    @Provides
    @ActivityScoped
    fun provideTask(): TaskData {
        return TaskData("AOA M Younas")
    }

}