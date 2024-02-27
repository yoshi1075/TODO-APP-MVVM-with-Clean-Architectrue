package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalMaterial3Api
@Composable
fun TodoListScreen(
    state: TodoListUiState,
    onEvent: (TodoListEvent) -> Unit,
    navigateToTodoRegisterScreen: () -> Unit,
    navigateToTodoEditScreen: (Int) -> Unit,
) {
    LaunchedEffect(Unit) {
        onEvent(TodoListEvent.OnLaunched)
    }

    Scaffold(
        topBar = { TodoListAppBar() },
        floatingActionButton = { AddingTodoFloatingButton(navigateToTodoRegisterScreen) },
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            items(state.todos) { todo ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable { navigateToTodoEditScreen(todo.id) }
                        .fillMaxWidth()
                ) {
                    Checkbox(
                        checked = todo.isDone,
                        onCheckedChange = {
                            onEvent(TodoListEvent.OnChangedTodoChecked(todo, it))
                        }
                    )
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

        if (state.showsLoadingDialog) {
            Box(
                contentAlignment= Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(100.dp),
                    strokeWidth = 16.dp,
                )
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
fun AddingTodoFloatingButton(navigateToTodoRegisterScreen: () -> Unit) {
    FloatingActionButton(
        onClick = { navigateToTodoRegisterScreen() }) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
    }
}
