package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_app_mvvm_with_clean_architectrue.data.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TodoEditViewModel(private val todoRepository: TodoRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(TodoEditUiState())
    val uiState = _uiState.asStateFlow()

    fun onLaunched(todoId: Int) {
        viewModelScope.launch {
            todoRepository.getTodo(todoId).let { todo ->
                _uiState.update {
                    uiState.value.copy(todo = todo, title = todo.title, detail = todo.detail)
                }
            }
        }
    }

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

    fun onDeleteIconTapped() {
        _uiState.update {
            uiState.value.copy(showsDialog = true)
        }
    }

    fun onDeleteConfirmed() {
        viewModelScope.launch {
            todoRepository.deleteTodo(todo = uiState.value.todo)
            _uiState.update {
                uiState.value.copy(showsDialog = false)
            }
        }
    }

    fun onDialogDismissRequested() {
        _uiState.update {
            uiState.value.copy(showsDialog = false)
        }
    }

    fun onCompleteIconTapped() {
        viewModelScope.launch {
            val newTodo = uiState.value.todo.copy(
                title = uiState.value.title,
                detail = uiState.value.detail
            )
            todoRepository.updateTodo(newTodo)
        }
    }
}
