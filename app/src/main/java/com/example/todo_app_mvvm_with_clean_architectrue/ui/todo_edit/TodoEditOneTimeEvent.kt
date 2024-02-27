package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_edit

sealed interface TodoEditOneTimeEvent {
    object Nothing : TodoEditOneTimeEvent
    data class ShowSnackbar(val message: String) : TodoEditOneTimeEvent
    object NavigateToListScreen : TodoEditOneTimeEvent
}
