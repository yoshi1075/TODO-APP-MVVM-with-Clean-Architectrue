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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo_app_mvvm_with_clean_architectrue.ui.common.ObserveAsEvent
import com.example.todo_app_mvvm_with_clean_architectrue.ui.navigation.toolbar.TodoEditToolbar
import com.example.todo_app_mvvm_with_clean_architectrue.ui.shared.SharedEvent
import com.example.todo_app_mvvm_with_clean_architectrue.ui.shared.SharedState

@Composable
fun TodoEditScreen(
    sharedState: SharedState,
    onSharedEvent: (SharedEvent) -> Unit,
    state: TodoEditUiState,
    onEvent: (TodoEditEvent) -> Unit,
    backToTodoListScreen: () -> Unit,
) {
    LaunchedEffect(Unit) {
        onEvent(TodoEditEvent.OnLaunched)
    }

    LaunchedEffect(state.oneTimeEvent) {
        when (val event = state.oneTimeEvent) {
            TodoEditOneTimeEvent.NavigateToListScreen -> {
                backToTodoListScreen()
                onEvent(TodoEditEvent.OnEventConsumed)
            }

            is TodoEditOneTimeEvent.ShowSnackbar -> {
                onSharedEvent(SharedEvent.ShowSnackbar(event.message))
                onEvent(TodoEditEvent.OnEventConsumed)
                backToTodoListScreen()
            }

            TodoEditOneTimeEvent.Nothing -> {}
        }
    }

    ObserveAsEvent(TodoEditToolbar.event) { event ->
        when (event) {
            TodoEditToolbar.Event.OnNavigationIconTapped -> {
                backToTodoListScreen()
            }

            TodoEditToolbar.Event.OnDeleteIconIconTapped -> {
                onEvent(TodoEditEvent.OnDeleteIconTapped)
            }

            TodoEditToolbar.Event.OnCompleteIconIconTapped -> {
                onEvent(TodoEditEvent.OnCompleteIconTapped)
            }
        }
    }

    if (state.showsDialog) {
        AlertDialog(
            onDismissRequest = {},
            confirmButton = {
                Button(
                    onClick = {
                        // TODO: 削除処理
                        onEvent(TodoEditEvent.OnDeleteConfirmed)
                    },
                ) {
                    Text("削除")
                }
            },
            dismissButton = {
                Button(
                    onClick = { onEvent(TodoEditEvent.OnDialogDismissRequested) },
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
            .padding(horizontal = 16.dp),
    ) {
        Text(
            text = "タイトル",
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
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
