package com.example.todo_app_mvvm_with_clean_architectrue.ui.shared

sealed interface SharedOneTimeEvent {
    data object Nothing : SharedOneTimeEvent
    data class ShowSnackbar(val message: String) : SharedOneTimeEvent
}
