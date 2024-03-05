package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_app_mvvm_with_clean_architectrue.data.TodoRepository
import com.example.todo_app_mvvm_with_clean_architectrue.ui.extensions.saveableMutableStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class TodoEditViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val todoRepository: TodoRepository,
) : ViewModel() {
    private val _uiState by savedStateHandle.saveableMutableStateFlow {
        MutableStateFlow(TodoEditUiState())
    }
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: TodoEditEvent) {
        when (event) {
            is TodoEditEvent.OnLaunched -> {
                viewModelScope.launch {
                    val todoId = checkNotNull(savedStateHandle.get<Int>("todoId"))
                    todoRepository.getTodo(todoId).let { todo ->
                        _uiState.update { oldState ->
                            if (oldState.title.isBlank()) {
                                uiState.value.copy(todo = todo, title = todo.title, detail = todo.detail)
                            } else {
                                uiState.value.copy(todo = todo, title = oldState.title, detail = oldState.detail)
                            }
                        }
                    }
                }
            }

            is TodoEditEvent.OnTitleUpdated -> {
                _uiState.update {
                    uiState.value.copy(title = event.title)
                }
            }

            is TodoEditEvent.OnDetailUpdated -> {
                _uiState.update {
                    uiState.value.copy(detail = event.detail)
                }
            }

            TodoEditEvent.OnDeleteIconTapped -> {
                _uiState.update {
                    uiState.value.copy(showsDialog = true)
                }
            }

            TodoEditEvent.OnDeleteConfirmed -> {
                viewModelScope.launch {
                    todoRepository.deleteTodo(todo = uiState.value.todo)
                    _uiState.update {
                        uiState.value.copy(
                            showsDialog = false,
                            oneTimeEvent = TodoEditOneTimeEvent.ShowSnackbar("Todoを削除しました"),
                        )
                        uiState.value.copy(oneTimeEvent = TodoEditOneTimeEvent.NavigateToListScreen)
                    }
                }
            }

            TodoEditEvent.OnDialogDismissRequested -> {
                _uiState.update {
                    uiState.value.copy(showsDialog = false)
                }
            }

            TodoEditEvent.OnCompleteIconTapped -> {
                viewModelScope.launch {
                    val newTodo = uiState.value.todo.copy(
                        title = uiState.value.title,
                        detail = uiState.value.detail,
                    )
                    todoRepository.updateTodo(newTodo)
                    _uiState.update {
                        uiState.value.copy(oneTimeEvent = TodoEditOneTimeEvent.ShowSnackbar("更新に成功しました"))
                        uiState.value.copy(oneTimeEvent = TodoEditOneTimeEvent.NavigateToListScreen)
                    }
                }
            }

            TodoEditEvent.OnEventConsumed -> {
                _uiState.update {
                    uiState.value.copy(oneTimeEvent = TodoEditOneTimeEvent.Nothing)
                }
            }
        }
    }
}
