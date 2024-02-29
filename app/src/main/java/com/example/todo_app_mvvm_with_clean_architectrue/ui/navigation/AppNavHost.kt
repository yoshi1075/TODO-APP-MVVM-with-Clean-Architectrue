@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.todo_app_mvvm_with_clean_architectrue.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_edit.TodoEditScreen
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_edit.TodoEditViewModel
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_list.TodoListScreen
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_list.TodoListViewModel
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_register.TodoRegisterScreen
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_register.TodoRegisterViewModel

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.List.route,
    ) {
        composable(NavigationItem.List.route) {
            val viewModel: TodoListViewModel = hiltViewModel()
            val state by viewModel.uiState.collectAsState()
            TodoListScreen(
                state,
                viewModel::onEvent,
                navigateToTodoRegisterScreen = { navController.navigate(NavigationItem.Register.route) },
                navigateToTodoEditScreen = { todoId -> navController.navigate(NavigationItem.Edit.route + "/$todoId") }
            )
        }
        composable(NavigationItem.Register.route) {
            val viewModel: TodoRegisterViewModel = hiltViewModel()
            val state by viewModel.uiState.collectAsState()
            TodoRegisterScreen(
                state,
                viewModel.oneTimeEvent,
                viewModel::onEvent,
                backToTodoListScreen = { navController.popBackStack() }
            )
        }
        composable(
            NavigationItem.Edit.route + "/{todoId}",
            arguments = listOf(navArgument("todoId") { type = NavType.IntType }),
        ) {
            val viewModel: TodoEditViewModel = hiltViewModel()
            val state by viewModel.uiState.collectAsState()
            TodoEditScreen(
                state,
                viewModel::onEvent,
                backToTodoListScreen = { navController.popBackStack() },
                todoId = it.arguments?.getInt("todoId") ?: 0
            )
        }
    }
}
