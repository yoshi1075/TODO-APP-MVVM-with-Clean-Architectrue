package com.example.todo_app_mvvm_with_clean_architectrue.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todo_app_mvvm_with_clean_architectrue.data.Todo

@Entity(tableName = "todos")
data class TodoEntity(
    @ColumnInfo(name = "todo_id") @PrimaryKey val todoId: Int,
    val title: String,
    val detail: String,
    @ColumnInfo(name = "is_done") val isDone: Boolean
) {
    fun toTodo(): Todo {
        return Todo(id = todoId, title = title, detail = detail, isDone = isDone)
    }

    companion object {
        fun fromTodo(todo: Todo): TodoEntity {
            return TodoEntity(todo.id, todo.title, todo.detail, todo.isDone)
        }
    }
}
