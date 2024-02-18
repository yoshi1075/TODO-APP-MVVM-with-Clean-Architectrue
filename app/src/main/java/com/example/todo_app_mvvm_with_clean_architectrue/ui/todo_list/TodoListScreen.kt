package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todo_app_mvvm_with_clean_architectrue.data.Todo
import com.example.todo_app_mvvm_with_clean_architectrue.ui.theme.TODOAPPMVVMwithCleanArchitectrueTheme


@Composable
fun TodoListScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(50) { index ->
            val todo = Todo(title =  "title$index")
            Text(
                todo.title,
                modifier = Modifier.padding(8.dp)
            )
            Divider(thickness = 1.dp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoListScreenPreview() {
    TODOAPPMVVMwithCleanArchitectrueTheme {
        Surface {
            TodoListScreen()
        }
    }
}