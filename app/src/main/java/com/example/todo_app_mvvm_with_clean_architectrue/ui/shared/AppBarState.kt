package com.example.todo_app_mvvm_with_clean_architectrue.ui.shared

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.todo_app_mvvm_with_clean_architectrue.ui.navigation.toolbar.Toolbar

class AppBarState(private val navController: NavController) {
    val currentScreenRoute: String?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route

    val currentToolbar: Toolbar?
        @Composable get() = Toolbar.findScreen(currentScreenRoute)

    val isVisible: Boolean
        @Composable get() = currentToolbar?.isAppBarVisible == true

    val navigationIcon: ImageVector?
        @Composable get() = currentToolbar?.navigationIcon

    val navigationIconContentDescription: String?
        @Composable get() = currentToolbar?.navigationIconContentDescription

    val onNavigationIconClick: (() -> Unit)?
        @Composable get() = currentToolbar?.onNavigationIconClick

    val title: String
        @Composable get() = currentToolbar?.title.orEmpty()

    val actions: @Composable RowScope.() -> Unit
        @Composable get() = currentToolbar?.actions ?: {}
}
