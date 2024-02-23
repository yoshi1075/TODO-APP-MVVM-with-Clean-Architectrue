package com.example.todo_app_mvvm_with_clean_architectrue.data

import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun getTodo(todoId: Int): Flow<Todo>
    suspend fun getTodos(): Flow<List<Todo>>
    suspend fun registerTodo(todo: Todo): Flow<Boolean>
    suspend fun updateTodo(todo: Todo): Flow<List<Todo>>
    suspend fun deleteTodo(todoId: Int): Flow<Boolean>
}
