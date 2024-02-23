package com.example.todo_app_mvvm_with_clean_architectrue.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object TodoRepositoryMock : TodoRepository {
    private var todos: List<Todo> = listOf(
        Todo(title = "title", detail = "detail")
    )

    override suspend fun getTodos(): Flow<List<Todo>> = flow {
        delay(500L)
        emit(todos)
    }

    override suspend fun getTodo(todoId: Int): Flow<Todo> = flow {
        delay(300L)
        val todo = todos.find { it.id == todoId } ?: return@flow
        emit(todo)
    }

    override suspend fun registerTodo(todo: Todo): Flow<Boolean> = flow {
        delay(300L)
        val tempTodos = todos.toMutableList()
        val isSuccess = tempTodos.add(todo)
        todos = tempTodos
        emit(isSuccess)
    }

    override suspend fun updateTodo(todo: Todo): Flow<List<Todo>> = flow {
        delay(300L)
        todos = todos.map {
            return@map if (it.id == todo.id) todo else it
        }
            .toMutableList()
        emit(todos)
    }

    override suspend fun deleteTodo(todoId: Int): Flow<Boolean> = flow {
        delay(300L)
        val todo = todos.find { it.id == todoId } ?: return@flow emit(false)
        val tempTodos = todos.toMutableList()
        val isSuccess = tempTodos.remove(todo)
        todos = tempTodos
        emit(isSuccess)
    }
}
