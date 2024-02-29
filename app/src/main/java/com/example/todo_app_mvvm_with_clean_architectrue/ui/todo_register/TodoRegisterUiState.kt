package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_register

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TodoRegisterUiState(
    val title: String = "",
    val detail: String = "",
) : Parcelable
