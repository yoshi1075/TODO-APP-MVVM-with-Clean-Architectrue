package com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo_app_mvvm_with_clean_architectrue.data.Todo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoEditScreen(todoId: Int, backToTodoListScreen: () -> Unit) {
    val todo = Todo(title = "title$todoId")
    val titleText = remember { mutableStateOf(todo.title) }
    val detailText = remember { mutableStateOf(todo.detail) }
    var showsDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TodoEditAppBar({ showsDialog = true }, backToTodoListScreen) },
    ) {
        if (showsDialog) {
            AlertDialog(
                onDismissRequest = { showsDialog = false },
                confirmButton = {
                    Button(
                        onClick = {
                            // TODO: 削除処理
                            showsDialog = false
                        }
                    ) {
                        Text("削除")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { showsDialog = false }
                    ) {
                        Text("キャンセル")
                    }
                },
                title = { Text(text = "Todoを削除しますか？") },
                text = { Text(text = "この動作は取り消せません") },
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(it)
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = "タイトル",
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
            TextField(
                value = titleText.value,
                onValueChange = { titleText.value = it },
                textStyle = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "詳細",
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth(),
            )
            TextField(
                value = detailText.value,
                onValueChange = { detailText.value = it },
                textStyle = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(480.dp),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoEditAppBar(showDialog: () -> Unit, backToTodoListScreen: () -> Unit) {
    TopAppBar(
        title = { Text("Todo編集", color = Color.White) },
        navigationIcon = {
            IconButton(
                onClick = { backToTodoListScreen() }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Arrow",
                    tint = Color.White,
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Blue),
        actions = {
            IconButton(
                onClick = { showDialog() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete",
                    tint = Color.White,
                )
            }
            IconButton(
                onClick = { backToTodoListScreen() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Check",
                    tint = Color.White,
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TodoEditScreenPreview() {
    TodoEditScreen(1, {})
}