package com.example.todo_app_mvvm_with_clean_architectrue.ui.shared

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SharedState(val title: String = "") : Parcelable
