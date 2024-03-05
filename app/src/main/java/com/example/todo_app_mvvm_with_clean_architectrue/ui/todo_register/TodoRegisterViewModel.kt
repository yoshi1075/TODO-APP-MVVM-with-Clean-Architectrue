package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_register

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_app_mvvm_with_clean_architectrue.data.Todo
import com.example.todo_app_mvvm_with_clean_architectrue.data.TodoRepository
import com.example.todo_app_mvvm_with_clean_architectrue.ui.extensions.saveableMutableStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class TodoRegisterViewModel @Inject constructor(
    private val todoRepository: TodoRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState by savedStateHandle.saveableMutableStateFlow {
        MutableStateFlow(TodoRegisterUiState())
    }
    val uiState = _uiState.asStateFlow()

    private val _oneTimeEvent = Channel<TodoRegisterOneTimeEvent>()
    val oneTimeEvent = _oneTimeEvent.receiveAsFlow()

    fun onEvent(event: TodoRegisterEvent) {
        when (event) {
            is TodoRegisterEvent.OnTitleUpdated -> {
                _uiState.update {
                    uiState.value.copy(title = event.title)
                }
            }

            is TodoRegisterEvent.OnDetailUpdated -> {
                _uiState.update {
                    uiState.value.copy(detail = event.detail)
                }
            }

            TodoRegisterEvent.OnRegisterButtonTapped -> {
                viewModelScope.launch {
                    val todo = Todo(
                        title = uiState.value.title,
                        detail = uiState.value.detail,
                    )
                    todoRepository.registerTodo(todo)
                    _oneTimeEvent.send(TodoRegisterOneTimeEvent.ShowSnackbar("Success"))
                }
            }

            TodoRegisterEvent.BackTodoListScreen -> {
                viewModelScope.launch {
                    _oneTimeEvent.send(TodoRegisterOneTimeEvent.NavigateToListScreen)
                }
            }
        }
    }
}
