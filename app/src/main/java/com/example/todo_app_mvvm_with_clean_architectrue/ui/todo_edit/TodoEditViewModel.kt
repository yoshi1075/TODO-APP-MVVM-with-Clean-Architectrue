package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_edit

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_app_mvvm_with_clean_architectrue.data.Todo
import com.example.todo_app_mvvm_with_clean_architectrue.data.TodoRepositoryMock
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TodoEditViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TodoEditUiState())
    val uiState = _uiState.asStateFlow()

    fun onLaunched(todoId: Int) {
        viewModelScope.launch {
            TodoRepositoryMock.getTodo(todoId)
                .onEach { todo ->
                    _uiState.update {
                        uiState.value.copy(todoId = todoId, title = todo.title, detail = todo.detail)
                    }
                }
                .launchIn(this)
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
            TodoRepositoryMock.deleteTodo(todoId = uiState.value.todoId)
                .onEach { isSuccess ->
                    _uiState.update {
                        uiState.value.copy(showsDialog = false)
                    }
                }
                .launchIn(this)
        }
    }

    fun onDialogDismissRequested() {
        _uiState.update {
            uiState.value.copy(showsDialog = false)
        }
    }

    fun onCompleteIconTapped() {
        viewModelScope.launch {
            val todo = Todo(
                title = uiState.value.title,
                detail = uiState.value.detail
            )
            TodoRepositoryMock.updateTodo(todo)
                .onEach { isSuccess ->
                    // TODO: display result
                    Log.d("register isSuccess: ", isSuccess.toString())
                }
                .launchIn(this)
        }
    }
}
