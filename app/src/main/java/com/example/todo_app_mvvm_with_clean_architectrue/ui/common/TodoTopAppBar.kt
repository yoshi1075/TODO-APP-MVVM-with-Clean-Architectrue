@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.todo_app_mvvm_with_clean_architectrue.ui.common

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.todo_app_mvvm_with_clean_architectrue.ui.shared.AppBarState

@Composable
fun TodoTopAppBar(appBarState: AppBarState, modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            val title = appBarState.title
            if (title.isNotEmpty()) {
                Text(text = title, color = Color.White)
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Blue),
        navigationIcon = {
            val icon = appBarState.navigationIcon
            val callback = appBarState.onNavigationIconClick
            if (icon != null) {
                IconButton(
                    onClick = { callback?.invoke() },
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = appBarState.navigationIconContentDescription,
                        tint = Color.White,
                    )
                }
            }
        },
        actions = appBarState.actions,
        modifier = modifier,
    )
}
