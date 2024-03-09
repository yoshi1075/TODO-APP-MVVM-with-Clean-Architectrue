package com.example.todo_app_mvvm_with_clean_architectrue.ui.navigation

sealed class NavigationItem(val route: String) {
    data object List : NavigationItem("list")
    data object Register : NavigationItem("register")
    data object Edit : NavigationItem("edit")
}
