package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_edit

sealed interface TodoEditEvent {
    object OnLaunched: TodoEditEvent
    data class OnTitleUpdated(val title: String) : TodoEditEvent
    data class OnDetailUpdated(val detail: String) : TodoEditEvent
    object OnDeleteIconTapped : TodoEditEvent
    object OnDeleteConfirmed : TodoEditEvent
    object OnDialogDismissRequested : TodoEditEvent
    object OnCompleteIconTapped : TodoEditEvent
    object OnSnackbarDismissed : TodoEditEvent
    object OnEventConsumed : TodoEditEvent
}
