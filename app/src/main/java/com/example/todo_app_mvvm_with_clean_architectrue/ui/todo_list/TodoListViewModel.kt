package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_list

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

class TodoListViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<TodoListUiState> = MutableStateFlow(TodoListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getTodo()
                .onEach { todos ->
                    _uiState.update { uiState.value.copy(todos = todos ) }
                }
                .launchIn(this)
        }
    }

    fun onChangedTodoChecked(todo: Todo, isChecked: Boolean) {
        val newTodo = todo.copy(isDone = isChecked)
        viewModelScope.launch {
            updateTodo(newTodo)
                .onEach { todos ->
                    _uiState.update {
                        uiState.value.copy(todos = todos)
                    }
                }
                .launchIn(this)
        }
    }

    private suspend fun getTodo(): Flow<List<Todo>> = flow {
        delay(500L)
        val todos = mutableListOf<Todo>()
        for (i in 1..50) {
            todos.add(
                Todo(title = "title$i")
            )
        }
        emit(todos)
    }

    private suspend fun updateTodo(todo: Todo): Flow<List<Todo>> = flow {
        val todos = uiState.value.todos.map {
            return@map if (it.id == todo.id) { todo } else { it }
        }
        delay(300L)
        emit(todos)
    }
}
