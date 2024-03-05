package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo_app_mvvm_with_clean_architectrue.ui.common.ObserveAsEvent
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_register.components.TodoRegisterAppBar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoRegisterScreen(
    uiState: TodoRegisterUiState,
    oneTimeEvent: Flow<TodoRegisterOneTimeEvent>,
    onEvent: (TodoRegisterEvent) -> Unit,
    backToTodoListScreen: () -> Unit,
) {
    val hostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    ObserveAsEvent(oneTimeEvent) { event ->
        when (event) {
            TodoRegisterOneTimeEvent.NavigateToListScreen -> {
                backToTodoListScreen()
            }

            is TodoRegisterOneTimeEvent.ShowSnackbar -> {
                scope.launch {
                    hostState.showSnackbar(
                        message = event.message,
                        duration = SnackbarDuration.Short,
                        withDismissAction = true,
                    )
                }
            }
        }
    }

    Scaffold(
        topBar = { TodoRegisterAppBar(backToTodoListScreen, onEvent) },
        snackbarHost = { SnackbarHost(hostState) },
    ) {
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
                    .padding(top = 8.dp),
            )
            TextField(
                value = uiState.title,
                onValueChange = { onEvent(TodoRegisterEvent.OnTitleUpdated(it)) },
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
                value = uiState.detail,
                onValueChange = { onEvent(TodoRegisterEvent.OnDetailUpdated(it)) },
                textStyle = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(480.dp),
            )
        }
    }
}
