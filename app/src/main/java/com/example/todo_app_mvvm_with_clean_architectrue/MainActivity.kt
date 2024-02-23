package com.example.todo_app_mvvm_with_clean_architectrue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.example.todo_app_mvvm_with_clean_architectrue.data.TodoRepositoryImpl
import com.example.todo_app_mvvm_with_clean_architectrue.database.TodoDatabase
import com.example.todo_app_mvvm_with_clean_architectrue.ui.theme.TODOAPPMVVMwithCleanArchitectrueTheme
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_edit.TodoEditScreen
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_edit.TodoEditViewModel
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_list.TodoListScreen
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_list.TodoListViewModel
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_register.TodoRegisterScreen
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_register.TodoRegisterViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java, "todo-database"
        ).build()
        val todoDao = db.dao
        val todoRepository = TodoRepositoryImpl(todoDao)

        setContent {
            TODOAPPMVVMwithCleanArchitectrueTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "list",
                ) {
                    composable("list") {
                        TodoListScreen(
                            viewModel { TodoListViewModel(todoRepository) },
                            navigateToTodoRegisterScreen = { navController.navigate("register") },
                            navigateToTodoEditScreen = { todoId -> navController.navigate("edit/$todoId") }
                        )
                    }
                    composable("register") {
                        TodoRegisterScreen(
                            viewModel { TodoRegisterViewModel(todoRepository) },
                            backToTodoListScreen = { navController.popBackStack() }
                        )
                    }
                    composable(
                        "edit/{todoId}",
                        arguments = listOf(navArgument("todoId") { type = NavType.IntType })
                    ) {
                        TodoEditScreen(
                            viewModel { TodoEditViewModel(todoRepository) },
                            backToTodoListScreen = { navController.popBackStack() },
                            todoId = it.arguments?.getInt("todoId") ?: 0
                        )
                    }
                }
            }
        }
    }
}
