package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.todo_app_mvvm_with_clean_architectrue.data.Todo
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_list.TodoListEvent

@Composable
fun TodoListItem(todo: Todo, navigateToTodoEditScreen: (Int) -> Unit, onEvent: (TodoListEvent) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { navigateToTodoEditScreen(todo.id) }
            .fillMaxWidth(),
    ) {
        Checkbox(
            checked = todo.isDone,
            onCheckedChange = {
                onEvent(TodoListEvent.OnChangedTodoChecked(todo, it))
            },
        )
        Text(
            todo.title,
            fontSize = 16.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
