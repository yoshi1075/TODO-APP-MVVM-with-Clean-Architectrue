package com.example.todo_app_mvvm_with_clean_architectrue.ui.navigation

sealed class Screen(val route: String) {
    data object List : Screen("list")
    data object Register : Screen("register")
    data object Edit : Screen("edit")
}
