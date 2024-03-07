package com.example.todo_app_mvvm_with_clean_architectrue.ui.shared

import com.example.todo_app_mvvm_with_clean_architectrue.ui.navigation.Screen

sealed interface SharedEvent {
    data class OnScreenLaunched(val screen: Screen) : SharedEvent
    data class ShowSnackbar(val message: String) : SharedEvent
}
