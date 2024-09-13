package com.smarttasks.domain

import com.smarttasks.domain.usecases.GetTasksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule{
    @Provides
    @ViewModelScoped
    fun provideGetTasksUseCase(): GetTasksUseCase {
        return GetTasksUseCase()
    }

}