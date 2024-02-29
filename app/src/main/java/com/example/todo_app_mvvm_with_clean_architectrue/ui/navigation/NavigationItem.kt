package com.example.todo_app_mvvm_with_clean_architectrue.ui.navigation

sealed class NavigationItem(val route: String) {
    data object List : NavigationItem(Screen.LIST.name)
    data object Register : NavigationItem(Screen.REGISTER.name)
    data object Edit : NavigationItem(Screen.EDIT.name)
}
