package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_edit

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed interface TodoEditOneTimeEvent : Parcelable {
    @Parcelize
    data object Nothing : TodoEditOneTimeEvent
    @Parcelize
    data class ShowSnackbar(val message: String) : TodoEditOneTimeEvent
    @Parcelize
    data object NavigateToListScreen : TodoEditOneTimeEvent
}
