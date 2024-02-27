package com.example.todo_app_mvvm_with_clean_architectrue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todo_app_mvvm_with_clean_architectrue.ui.theme.TODOAPPMVVMwithCleanArchitectrueTheme
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_edit.TodoEditScreen
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_edit.TodoEditViewModel
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_list.TodoListScreen
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_list.TodoListViewModel
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_register.TodoRegisterScreen
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_register.TodoRegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TODOAPPMVVMwithCleanArchitectrueTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "list",
                ) {
                    composable("list") {
                        val viewModel: TodoListViewModel = hiltViewModel()
                        val state by viewModel.uiState.collectAsState()
                        TodoListScreen(
                            state,
                            viewModel::onEvent,
                            navigateToTodoRegisterScreen = { navController.navigate("register") },
                            navigateToTodoEditScreen = { todoId -> navController.navigate("edit/$todoId") }
                        )
                    }
                    composable("register") {
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
                        "edit/{todoId}",
                        arguments = listOf(navArgument("todoId") { type = NavType.IntType })
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
        }
    }
}
