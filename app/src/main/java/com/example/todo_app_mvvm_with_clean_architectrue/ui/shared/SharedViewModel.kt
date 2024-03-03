package com.example.todo_app_mvvm_with_clean_architectrue.ui.shared

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.todo_app_mvvm_with_clean_architectrue.ui.extensions.saveableMutableStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _sharedState by savedStateHandle.saveableMutableStateFlow {
        MutableStateFlow(SharedState())
    }
    val sharedState = _sharedState.asStateFlow()

    fun onEvent(event: SharedEvent) {
        // TODO: impl
        when (event) {
            is SharedEvent.OnScreenLaunched -> {
                _sharedState.update { sharedState.value.copy(title = event.screen.route)  }
            }
        }
    }
}
