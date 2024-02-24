package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_app_mvvm_with_clean_architectrue.data.Todo
import com.example.todo_app_mvvm_with_clean_architectrue.data.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoRegisterViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(TodoRegisterUiState())
    val uiState = _uiState.asStateFlow()

    private val _oneTimeEvent = Channel<TodoRegisterEvent>()
    val oneTimeEvent = _oneTimeEvent.receiveAsFlow()

    fun onTitleUpdated(title: String) {
        _uiState.update {
            uiState.value.copy(title = title)
        }
    }

    fun onDetailUpdated(detail: String) {
        _uiState.update {
            uiState.value.copy(detail = detail)
        }
    }

    fun onRegisterButtonTapped() {
        viewModelScope.launch {
            val todo = Todo(
                title = uiState.value.title,
                detail = uiState.value.detail
            )
            todoRepository.registerTodo(todo)
            _oneTimeEvent.send(TodoRegisterEvent.ShowSnackbar("Success"))
        }
    }

    suspend fun backTodoListScreen() {
        _oneTimeEvent.send(TodoRegisterEvent.NavigateToListScreen)
    }
}
