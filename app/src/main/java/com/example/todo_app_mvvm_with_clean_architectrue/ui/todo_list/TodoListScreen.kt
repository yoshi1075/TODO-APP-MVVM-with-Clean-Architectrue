package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todo_app_mvvm_with_clean_architectrue.ui.theme.TODOAPPMVVMwithCleanArchitectrueTheme

@ExperimentalMaterial3Api
@Composable
fun TodoListScreen(
    todoListViewModel: TodoListViewModel = viewModel(),
    navigateToTodoRegisterScreen: () -> Unit,
    navigateToTodoEditScreen: (Int) -> Unit,
) {
    val uiState by todoListViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        todoListViewModel.onLaunched()
    }

    Scaffold(
        topBar = { TodoListAppBar() },
        floatingActionButton = { AddingTodoFloatingButton(navigateToTodoRegisterScreen) },
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            items(uiState.todos) {todo ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable { navigateToTodoEditScreen(todo.id) }
                        .fillMaxWidth()
                ) {
                    Checkbox(
                        checked = todo.isDone,
                        onCheckedChange = {
                            todoListViewModel.onChangedTodoChecked(todo, it)
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun TodoListScreenPreview() {
    TODOAPPMVVMwithCleanArchitectrueTheme {
        TodoListScreen(navigateToTodoEditScreen = {}, navigateToTodoRegisterScreen = {})
    }
}