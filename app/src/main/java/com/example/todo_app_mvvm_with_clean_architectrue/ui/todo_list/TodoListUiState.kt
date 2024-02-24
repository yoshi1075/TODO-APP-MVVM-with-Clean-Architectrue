package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_list

import com.example.todo_app_mvvm_with_clean_architectrue.data.Todo

data class TodoListUiState(
    val showsLoadingDialog: Boolean = false,
    val todos: List<Todo> = emptyList(),
)
