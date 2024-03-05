package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_register

sealed interface TodoRegisterOneTimeEvent {
    data class ShowSnackbar(val message: String) : TodoRegisterOneTimeEvent
    data object NavigateToListScreen : TodoRegisterOneTimeEvent
}
