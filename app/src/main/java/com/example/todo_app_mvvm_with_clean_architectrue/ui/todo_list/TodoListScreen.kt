package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo_app_mvvm_with_clean_architectrue.data.Todo
import com.example.todo_app_mvvm_with_clean_architectrue.ui.theme.TODOAPPMVVMwithCleanArchitectrueTheme


@Composable
fun TodoListScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
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

@Preview(showBackground = true)
@Composable
fun TodoListScreenPreview() {
    TODOAPPMVVMwithCleanArchitectrueTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            TodoListScreen()
        }
    }
}