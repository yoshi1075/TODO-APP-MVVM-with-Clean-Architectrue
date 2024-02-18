package com.example.todo_app_mvvm_with_clean_architectrue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todo_app_mvvm_with_clean_architectrue.ui.theme.TODOAPPMVVMwithCleanArchitectrueTheme
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_list.TodoListScreen
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_register.TodoRegisterScreen

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
                        TodoListScreen(
                            navigateToTodoRegisterScreen = { navController.navigate("register") }
                        )
                    }
                    composable("register") {
                        TodoRegisterScreen(
                            backTodoListScreen = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
