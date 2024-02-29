package com.example.todo_app_mvvm_with_clean_architectrue.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

@Parcelize
data class Todo(
    val id: Int = Random.nextInt(),
    val title: String,
    val detail: String = "",
    val isDone: Boolean = false,
) : Parcelable
