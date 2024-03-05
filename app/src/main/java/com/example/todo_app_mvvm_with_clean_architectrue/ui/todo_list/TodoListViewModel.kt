package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_app_mvvm_with_clean_architectrue.data.TodoRepository
import com.example.todo_app_mvvm_with_clean_architectrue.ui.extensions.saveableMutableStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class TodoListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val todoRepository: TodoRepository,
) : ViewModel() {
    private val _uiState by savedStateHandle.saveableMutableStateFlow {
        MutableStateFlow(TodoListUiState())
    }
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: TodoListEvent) {
        when (event) {
            TodoListEvent.OnLaunched -> {
                viewModelScope.launch {
                    _uiState.update {
                        uiState.value.copy(showsLoadingDialog = true)
                    }
                    todoRepository.getTodos()
                        .onEach { todos ->
                            _uiState.update {
                                uiState.value.copy(
                                    todos = todos,
                                    showsLoadingDialog = false,
                                )
                            }
                        }
                        .launchIn(this)
                }
            }

            is TodoListEvent.OnChangedTodoChecked -> {
                val newTodo = event.todo.copy(isDone = event.isChecked)
                viewModelScope.launch {
                    todoRepository.updateTodo(newTodo)
                }
            }
        }
    }
}
