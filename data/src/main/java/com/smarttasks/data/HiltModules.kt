package com.smarttasks.data

import com.smarttasks.data.datasources.TaskDataSource
import com.smarttasks.data.datasources.TaskRemoteDataSource
import com.smarttasks.data.network.Endpoints
import com.smarttasks.data.repositories.TaskRepositoryImpl
import com.smarttasks.domain.repositories.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object Retrofit2Module {
    @Provides
    @ViewModelScoped
    fun provideRetrofit2(): Endpoints {
        val builder = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
        if (BuildConfig.DEBUG) {
            builder.client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            )
        }

        return builder.build().create(Endpoints::class.java)
    }
}

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {
    @Provides
    @ViewModelScoped
    fun provideTaskDataSource(endpoints: Endpoints): TaskDataSource {
        return TaskRemoteDataSource(endpoints)
    }
    @Provides
    @ViewModelScoped
    fun provideTaskRepository(dataSource: TaskDataSource): TaskRepository  {
        return TaskRepositoryImpl(dataSource)
    }

}