package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_app_mvvm_with_clean_architectrue.data.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoEditViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(TodoEditUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: TodoEditEvent) {
        when (event) {
            is TodoEditEvent.OnLaunched -> {
                viewModelScope.launch {
                    todoRepository.getTodo(event.todoId).let { todo ->
                        _uiState.update {
                            uiState.value.copy(todo = todo, title = todo.title, detail = todo.detail)
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
                            oneTimeEvent = TodoEditOneTimeEvent.ShowSnackbar("Todoを削除しました")
                        )
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
                        detail = uiState.value.detail
                    )
                    todoRepository.updateTodo(newTodo)
                    _uiState.update {
                        uiState.value.copy(oneTimeEvent = TodoEditOneTimeEvent.ShowSnackbar("更新に成功しました"))
                    }
                }
            }
            TodoEditEvent.OnSnackbarDismissed -> {
                _uiState.update {
                    uiState.value.copy(oneTimeEvent = TodoEditOneTimeEvent.NavigateToListScreen)
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
