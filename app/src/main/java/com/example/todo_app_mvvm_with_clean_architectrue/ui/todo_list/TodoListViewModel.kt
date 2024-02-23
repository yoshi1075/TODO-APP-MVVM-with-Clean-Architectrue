package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_list

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

class TodoListViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<TodoListUiState> = MutableStateFlow(TodoListUiState())
    val uiState = _uiState.asStateFlow()

    fun onLaunched() {
        viewModelScope.launch {
            TodoRepositoryMock.getTodos()
                .onEach { todos ->
                    _uiState.update { uiState.value.copy(todos = todos ) }
                }
                .launchIn(this)
        }
    }

    fun onChangedTodoChecked(todo: Todo, isChecked: Boolean) {
        val newTodo = todo.copy(isDone = isChecked)
        viewModelScope.launch {
            TodoRepositoryMock.updateTodo(newTodo)
                .onEach { todos ->
                    _uiState.update {
                        uiState.value.copy(todos = todos)
                    }
                }
                .launchIn(this)
        }
    }
}
