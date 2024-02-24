package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_register

sealed interface TodoRegisterEvent {
    data class ShowSnackbar(val message: String) : TodoRegisterEvent
    object NavigateToListScreen : TodoRegisterEvent
}
