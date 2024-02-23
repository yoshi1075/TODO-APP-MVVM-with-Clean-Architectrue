package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_edit

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_app_mvvm_with_clean_architectrue.data.Todo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TodoEditViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TodoEditUiState())
    val uiState = _uiState.asStateFlow()

    fun onLaunched(todoId: Int) {
        viewModelScope.launch {
            getTodo(todoId)
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
            deleteTodo(todoId = uiState.value.todoId)
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
            updateTodo(todo)
                .onEach { isSuccess ->
                    // TODO: display result
                    Log.d("register isSuccess: ", isSuccess.toString())
                }
                .launchIn(this)
        }
    }

    private suspend fun getTodo(todoId: Int): Flow<Todo> = flow {
        delay(500L)
        val todo = Todo(title = "title")
        emit(todo)
    }

    private suspend fun updateTodo(todo: Todo): Flow<Boolean> = flow {
        // TODO: DBのTodoを更新
        delay(500L)
        emit(true)
    }

    private suspend fun deleteTodo(todoId: Int): Flow<Boolean> = flow {
        delay(500L)
        emit(true)
    }
}
