package com.example.todo_app_mvvm_with_clean_architectrue.data

import com.example.todo_app_mvvm_with_clean_architectrue.database.TodoDao
import com.example.todo_app_mvvm_with_clean_architectrue.database.TodoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val dao: TodoDao
) : TodoRepository {
    override suspend fun getTodos(): Flow<List<Todo>> {
        return dao.getTodos().map { it.map { it.toTodo() } }
    }

    override suspend fun getTodo(todoId: Int): Todo {
        return dao.getTodoById(todoId).toTodo()
    }

    override suspend fun registerTodo(todo: Todo) {
        dao.insertTodo(TodoEntity.fromTodo(todo))
    }

    override suspend fun updateTodo(todo: Todo) {
        dao.updateTodo(TodoEntity.fromTodo(todo))
    }

    override suspend fun deleteTodo(todo: Todo) {
        dao.deleteTodo(TodoEntity.fromTodo(todo))
    }
}
