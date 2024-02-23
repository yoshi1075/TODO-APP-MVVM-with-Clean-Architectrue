package com.example.todo_app_mvvm_with_clean_architectrue.data

import com.example.todo_app_mvvm_with_clean_architectrue.database.TodoDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodoRepositoryImpl(
    private val dao: TodoDao
) : TodoRepository {
    override suspend fun getTodos(): Flow<List<Todo>> {
        return dao.getTodos().map { it.map { it.toTodo() } }
    }

    override suspend fun getTodo(todoId: Int): Todo {
        return dao.getTodoById(todoId).toTodo()
    }

    override suspend fun registerTodo(todo: Todo) {
        dao.insertTodo(todo.toTodoEntity())
    }

    override suspend fun updateTodo(todo: Todo) {
        dao.updateTodo(todo.toTodoEntity())
    }

    override suspend fun deleteTodo(todo: Todo) {
        dao.deleteTodo(todo.toTodoEntity())
    }
}
