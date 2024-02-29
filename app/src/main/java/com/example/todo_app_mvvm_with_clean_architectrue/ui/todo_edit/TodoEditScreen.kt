package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_edit.components.TodoEditAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoEditScreen(
    state: TodoEditUiState,
    onEvent: (TodoEditEvent) -> Unit,
    todoId: Int,
    backToTodoListScreen: () -> Unit,
) {
    val hostState = SnackbarHostState()

    LaunchedEffect(Unit) {
        onEvent(TodoEditEvent.OnLaunched(todoId))
    }

    LaunchedEffect(state.oneTimeEvent) {
        when (val event = state.oneTimeEvent) {
            TodoEditOneTimeEvent.NavigateToListScreen -> {
                backToTodoListScreen()
                onEvent(TodoEditEvent.OnEventConsumed)
            }
            is TodoEditOneTimeEvent.ShowSnackbar -> {
                val result = hostState.showSnackbar(
                    message = event.message,
                    duration = SnackbarDuration.Short,
                    withDismissAction = true,
                )
                when (result) {
                    SnackbarResult.Dismissed,
                    SnackbarResult.ActionPerformed -> {
                        onEvent(TodoEditEvent.OnSnackbarDismissed)
                    }
                }
                onEvent(TodoEditEvent.OnEventConsumed)
            }
            TodoEditOneTimeEvent.Nothing -> {}
        }
    }

    Scaffold(
        topBar = {
            TodoEditAppBar(
                backToTodoListScreen,
                onEvent,
            )
        },
        snackbarHost = { SnackbarHost(hostState) }
    ) {
        if (state.showsDialog) {
            AlertDialog(
                onDismissRequest = {},
                confirmButton = {
                    Button(
                        onClick = {
                            // TODO: 削除処理
                            onEvent(TodoEditEvent.OnDeleteConfirmed)
                        }
                    ) {
                        Text("削除")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { onEvent(TodoEditEvent.OnDialogDismissRequested) }
                    ) {
                        Text("キャンセル")
                    }
                },
                title = { Text(text = "Todoを削除しますか？") },
                text = { Text(text = "この動作は取り消せません") },
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(it)
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = "タイトル",
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
            TextField(
                value = state.title,
                onValueChange = { onEvent(TodoEditEvent.OnTitleUpdated(it)) },
                textStyle = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "詳細",
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth(),
            )
            TextField(
                value = state.detail,
                onValueChange = { onEvent(TodoEditEvent.OnDetailUpdated(it)) },
                textStyle = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(480.dp),
            )
        }
    }
}
