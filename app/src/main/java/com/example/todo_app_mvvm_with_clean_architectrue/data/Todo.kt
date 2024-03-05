package com.example.todo_app_mvvm_with_clean_architectrue.data

import android.os.Parcelable
import kotlin.random.Random
import kotlinx.parcelize.Parcelize

@Parcelize
data class Todo(
    val id: Int = Random.nextInt(),
    val title: String,
    val detail: String = "",
    val isDone: Boolean = false,
) : Parcelable
