package com.example.todo_app_mvvm_with_clean_architectrue.data

import com.example.todo_app_mvvm_with_clean_architectrue.database.TodoEntity
import kotlin.random.Random

data class Todo(
    val id: Int = Random.nextInt(),
    val title: String,
    val detail: String = "",
    val isDone: Boolean = false,
) {
    fun toTodoEntity(): TodoEntity {
        return TodoEntity(id, title, detail, isDone)
    }
}
