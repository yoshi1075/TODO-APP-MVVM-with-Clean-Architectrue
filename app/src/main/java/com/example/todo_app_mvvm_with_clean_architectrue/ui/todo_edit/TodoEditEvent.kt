package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_edit

sealed interface TodoEditEvent {
    object Nothing : TodoEditEvent
    data class ShowSnackbar(val message: String) : TodoEditEvent
    object NavigateToListScreen : TodoEditEvent
}
