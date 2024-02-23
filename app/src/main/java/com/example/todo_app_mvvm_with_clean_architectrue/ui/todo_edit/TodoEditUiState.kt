package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_edit

data class TodoEditUiState(
    val todoId: Int = 0,
    val title: String = "",
    val detail: String = "",
    val showsDialog: Boolean = false,
)
