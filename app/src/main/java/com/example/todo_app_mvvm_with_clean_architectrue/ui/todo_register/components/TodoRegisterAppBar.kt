package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_register.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_register.TodoRegisterEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoRegisterAppBar(
    backToTodoListScreen: () -> Unit,
    onEvent: (TodoRegisterEvent) -> Unit,
) {
    TopAppBar(
        title = { Text("Todo登録", color = Color.White) },
        navigationIcon = {
            IconButton(
                onClick = { backToTodoListScreen() }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Arrow",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Blue),
        actions = {
            IconButton(
                onClick = { onEvent(TodoRegisterEvent.OnRegisterButtonTapped) }
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Check",
                    tint = Color.White
                )
            }
        }
    )
}
