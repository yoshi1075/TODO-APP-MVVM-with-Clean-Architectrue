package com.example.todo_app_mvvm_with_clean_architectrue.data

import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun getTodos(): Flow<List<Todo>>
    suspend fun getTodo(todoId: Int): Todo
    suspend fun registerTodo(todo: Todo)
    suspend fun updateTodo(todo: Todo)
    suspend fun deleteTodo(todo: Todo)
}
