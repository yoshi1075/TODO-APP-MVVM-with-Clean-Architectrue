package com.example.todo_app_mvvm_with_clean_architectrue.ui.navigation.toolbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.todo_app_mvvm_with_clean_architectrue.ui.navigation.NavigationItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

data object TodoEditToolbar : Toolbar {
    sealed interface Event {
        data object OnNavigationIconTapped : Event
        data object OnDeleteIconIconTapped : Event
        data object OnCompleteIconIconTapped : Event
    }

    private val _Event = MutableSharedFlow<Event>(extraBufferCapacity = 1)
    val event: Flow<Event> = _Event.asSharedFlow()

    override val route: String = NavigationItem.Edit().route
    override val isAppBarVisible: Boolean = true
    override val navigationIcon: ImageVector = Icons.Filled.ArrowBack
    override val navigationIconContentDescription: String = "Back Arrow"
    override val onNavigationIconClick: () -> Unit = {
        _Event.tryEmit(Event.OnNavigationIconTapped)
    }
    override val title: String = "Todo編集"
    override val actions: @Composable (RowScope.() -> Unit) = {
        IconButton(
            onClick = { _Event.tryEmit(Event.OnDeleteIconIconTapped) },
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete",
                tint = Color.White,
            )
        }
        IconButton(
            onClick = { _Event.tryEmit(Event.OnCompleteIconIconTapped) },
        ) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "Check",
                tint = Color.White,
            )
        }
    }
}
