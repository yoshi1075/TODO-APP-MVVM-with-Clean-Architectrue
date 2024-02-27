package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_list

import com.example.todo_app_mvvm_with_clean_architectrue.data.Todo

sealed interface TodoListEvent {
    object OnLaunched : TodoListEvent
    data class OnChangedTodoChecked(val todo: Todo, val isChecked: Boolean) : TodoListEvent
}
