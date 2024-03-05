@file:OptIn(SavedStateHandleSaveableApi::class)

package com.example.todo_app_mvvm_with_clean_architectrue.ui.extensions

import androidx.compose.runtime.saveable.Saver
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadOnlyProperty
import kotlinx.coroutines.flow.MutableStateFlow

fun <T : Any> SavedStateHandle.saveableMutableStateFlow(
    init: () -> MutableStateFlow<T>,
): PropertyDelegateProvider<Any?, ReadOnlyProperty<Any?, MutableStateFlow<T>>> {
    return saveable(
        saver = Saver(
            save = {
                it.value
            },
            restore = {
                MutableStateFlow(it)
            },
        ),
        init = init,
    )
}
