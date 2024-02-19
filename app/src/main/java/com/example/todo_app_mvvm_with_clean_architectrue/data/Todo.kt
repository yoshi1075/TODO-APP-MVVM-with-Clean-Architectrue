package com.example.todo_app_mvvm_with_clean_architectrue.data

import java.util.UUID

data class Todo(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val isDone: Boolean = false,
)
