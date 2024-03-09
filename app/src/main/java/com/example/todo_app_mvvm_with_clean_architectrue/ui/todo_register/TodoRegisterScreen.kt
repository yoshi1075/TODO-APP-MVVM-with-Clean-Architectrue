package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo_app_mvvm_with_clean_architectrue.ui.common.ObserveAsEvent
import com.example.todo_app_mvvm_with_clean_architectrue.ui.navigation.toolbar.TodoRegisterToolbar
import com.example.todo_app_mvvm_with_clean_architectrue.ui.shared.SharedEvent
import com.example.todo_app_mvvm_with_clean_architectrue.ui.shared.SharedState
import kotlinx.coroutines.flow.Flow

@Composable
fun TodoRegisterScreen(
    sharedState: SharedState,
    onSharedEvent: (SharedEvent) -> Unit,
    uiState: TodoRegisterUiState,
    oneTimeEvent: Flow<TodoRegisterOneTimeEvent>,
    onEvent: (TodoRegisterEvent) -> Unit,
    backToTodoListScreen: () -> Unit,
) {
    ObserveAsEvent(oneTimeEvent) { event ->
        when (event) {
            TodoRegisterOneTimeEvent.NavigateToListScreen -> {
                backToTodoListScreen()
            }

            is TodoRegisterOneTimeEvent.ShowSnackbar -> {
                onSharedEvent(SharedEvent.ShowSnackbar(event.message))
            }
        }
    }

    ObserveAsEvent(TodoRegisterToolbar.event) { event ->
        when (event) {
            TodoRegisterToolbar.Event.OnNavigationIconTapped -> {
                backToTodoListScreen()
            }

            TodoRegisterToolbar.Event.OnRegisterButtonTapped -> {
                onEvent(TodoRegisterEvent.OnRegisterButtonTapped)
            }
        }
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
