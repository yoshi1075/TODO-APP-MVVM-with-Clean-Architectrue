package com.example.todo_app_mvvm_with_clean_architectrue.ui.navigation.toolbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.todo_app_mvvm_with_clean_architectrue.ui.navigation.NavigationItem

data object TodoListToolbar : Toolbar {
    override val route: String = NavigationItem.List.route
    override val isAppBarVisible: Boolean = true
    override val navigationIcon: ImageVector? = null
    override val navigationIconContentDescription: String? = null
    override val onNavigationIconClick: (() -> Unit)? = null
    override val title: String = "Todo一覧"
    override val actions: @Composable (RowScope.() -> Unit) = {}
}
