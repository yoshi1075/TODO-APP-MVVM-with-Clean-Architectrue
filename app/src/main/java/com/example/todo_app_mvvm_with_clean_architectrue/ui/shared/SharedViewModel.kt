package com.example.todo_app_mvvm_with_clean_architectrue.ui.shared

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_app_mvvm_with_clean_architectrue.ui.extensions.saveableMutableStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SharedViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _sharedState by savedStateHandle.saveableMutableStateFlow {
        MutableStateFlow(SharedState())
    }
    val sharedState = _sharedState.asStateFlow()

    private val _sharedOneTimeEvent = Channel<SharedOneTimeEvent>()
    val sharedOneTimeEvent = _sharedOneTimeEvent.receiveAsFlow()

    fun onEvent(event: SharedEvent) {
        // TODO: impl
        when (event) {
            is SharedEvent.OnScreenLaunched -> {
                _sharedState.update { sharedState.value.copy(title = event.screen.route) }
            }

            is SharedEvent.ShowSnackbar -> {
                viewModelScope.launch {
                    _sharedOneTimeEvent.send(SharedOneTimeEvent.ShowSnackbar(event.message))
                }
            }
        }
    }
}
