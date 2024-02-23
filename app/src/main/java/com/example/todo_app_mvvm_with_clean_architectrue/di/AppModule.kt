package com.example.todo_app_mvvm_with_clean_architectrue.di

import android.app.Application
import androidx.room.Room
import com.example.todo_app_mvvm_with_clean_architectrue.data.TodoRepository
import com.example.todo_app_mvvm_with_clean_architectrue.data.TodoRepositoryImpl
import com.example.todo_app_mvvm_with_clean_architectrue.database.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(app: Application): TodoDatabase {
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java, "todo-database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: TodoDatabase): TodoRepository {
        return TodoRepositoryImpl(db.dao)
    }
}
