package com.example.todo_app_mvvm_with_clean_architectrue.ui.shared

import com.example.todo_app_mvvm_with_clean_architectrue.ui.navigation.NavigationItem

sealed interface SharedEvent {
    data class OnScreenLaunched(val navigationItem: NavigationItem) : SharedEvent
    data class ShowSnackbar(val message: String) : SharedEvent
}
