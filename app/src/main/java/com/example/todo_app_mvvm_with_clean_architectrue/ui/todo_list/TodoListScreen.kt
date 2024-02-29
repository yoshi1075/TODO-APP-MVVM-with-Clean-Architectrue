package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_list.components.AddingTodoFloatingButton
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_list.components.TodoListAppBar
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_list.components.TodoListItem

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
                TodoListItem(
                    todo = todo,
                    navigateToTodoEditScreen = navigateToTodoEditScreen,
                    onEvent = onEvent
                )
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
