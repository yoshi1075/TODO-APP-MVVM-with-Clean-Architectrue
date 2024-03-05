package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_edit

sealed interface TodoEditEvent {
    data object OnLaunched : TodoEditEvent
    data class OnTitleUpdated(val title: String) : TodoEditEvent
    data class OnDetailUpdated(val detail: String) : TodoEditEvent
    data object OnDeleteIconTapped : TodoEditEvent
    data object OnDeleteConfirmed : TodoEditEvent
    data object OnDialogDismissRequested : TodoEditEvent
    data object OnCompleteIconTapped : TodoEditEvent
    data object OnEventConsumed : TodoEditEvent
}
