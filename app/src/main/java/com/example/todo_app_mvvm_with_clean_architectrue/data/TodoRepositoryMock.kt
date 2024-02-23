package com.example.todo_app_mvvm_with_clean_architectrue.data

import kotlinx.coroutines.flow.Flow

object TodoRepositoryMock : TodoRepository {
    private var todos: List<Todo> = listOf(
        Todo(title = "title", detail = "detail")
    )

    override suspend fun getTodos(): Flow<List<Todo>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTodo(todoId: Int): Todo {
        TODO("Not yet implemented")
    }

    override suspend fun registerTodo(todo: Todo) {
        TODO("Not yet implemented")
    }

    override suspend fun updateTodo(todo: Todo) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTodo(todo: Todo) {
        TODO("Not yet implemented")
    }
}
