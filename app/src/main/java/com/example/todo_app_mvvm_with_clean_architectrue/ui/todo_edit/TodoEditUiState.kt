package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_edit

import android.os.Parcelable
import com.example.todo_app_mvvm_with_clean_architectrue.data.Todo
import kotlinx.parcelize.Parcelize

@Parcelize
data class TodoEditUiState(
    val todo: Todo = Todo(title = "", detail = ""),
    val title: String = "",
    val detail: String = "",
    val showsDialog: Boolean = false,
    val oneTimeEvent: TodoEditOneTimeEvent = TodoEditOneTimeEvent.Nothing
) : Parcelable
