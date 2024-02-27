package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_register

sealed interface TodoRegisterEvent {
    data class OnTitleUpdated(val title: String) : TodoRegisterEvent
    data class OnDetailUpdated(val detail: String) : TodoRegisterEvent
    object OnRegisterButtonTapped : TodoRegisterEvent
    object BackTodoListScreen : TodoRegisterEvent
}