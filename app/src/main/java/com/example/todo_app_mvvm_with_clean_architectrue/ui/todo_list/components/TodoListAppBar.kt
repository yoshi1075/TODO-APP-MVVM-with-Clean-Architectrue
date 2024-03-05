package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_list.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListAppBar() {
    TopAppBar(
        title = { Text(text = "Todo一覧", color = Color.White) },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Blue),
    )
}
