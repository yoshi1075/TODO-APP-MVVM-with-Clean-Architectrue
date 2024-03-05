package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_list.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AddingTodoFloatingButton(navigateToTodoRegisterScreen: () -> Unit) {
    FloatingActionButton(
        onClick = { navigateToTodoRegisterScreen() },
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
    }
}

@Preview(
    widthDp = 100,
    heightDp = 100,
    showBackground = true,
)
@Composable
fun AddingTodoFloatingButtonPreview() {
    AddingTodoFloatingButton {}
}
