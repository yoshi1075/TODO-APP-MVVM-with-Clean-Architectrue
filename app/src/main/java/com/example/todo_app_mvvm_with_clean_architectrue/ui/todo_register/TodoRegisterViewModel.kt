package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_register

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

class TodoRegisterViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TodoRegisterUiState())
    val uiState = _uiState.asStateFlow()

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
            TodoRepositoryMock.registerTodo(todo)
                .onEach { isSuccess ->
                    // TODO: display result
                    Log.d("register isSuccess: ", isSuccess.toString())
                }
                .launchIn(this)
        }
    }
}
