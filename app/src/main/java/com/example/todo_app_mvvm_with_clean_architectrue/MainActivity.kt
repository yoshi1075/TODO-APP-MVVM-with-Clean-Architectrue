package com.example.todo_app_mvvm_with_clean_architectrue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.todo_app_mvvm_with_clean_architectrue.ui.theme.TODOAPPMVVMwithCleanArchitectrueTheme
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_list.TodoListScreen

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TODOAPPMVVMwithCleanArchitectrueTheme {
                TodoListScreen()
            }
        }
    }
}
