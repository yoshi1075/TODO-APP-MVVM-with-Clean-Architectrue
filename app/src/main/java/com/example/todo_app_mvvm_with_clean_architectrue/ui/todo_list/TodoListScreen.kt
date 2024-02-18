package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo_app_mvvm_with_clean_architectrue.data.Todo
import com.example.todo_app_mvvm_with_clean_architectrue.ui.theme.TODOAPPMVVMwithCleanArchitectrueTheme

@ExperimentalMaterial3Api
@Composable
fun TodoListScreen() {
    Scaffold(
        topBar = { TodoListAppBar() },
        floatingActionButton = { AddingTodoFloatingButton() },
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            items(50) { index ->
                val todo = Todo(title =  "title$index")
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Checkbox(checked = false, onCheckedChange = {})
                    Text(
                        todo.title,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Divider(thickness = 1.dp)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListAppBar() {
    TopAppBar(
        title = { Text(text = "Todo一覧", color = White) },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Blue)
    )
}

@Composable
fun AddingTodoFloatingButton() {
    FloatingActionButton(onClick = {}) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun TodoListScreenPreview() {
    TODOAPPMVVMwithCleanArchitectrueTheme {
        TodoListScreen()
    }
}