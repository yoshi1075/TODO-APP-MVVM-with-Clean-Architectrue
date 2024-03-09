package com.example.todo_app_mvvm_with_clean_architectrue.ui.navigation.toolbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

sealed interface Toolbar {
    val route: String
    val isAppBarVisible: Boolean
    val navigationIcon: ImageVector?
    val navigationIconContentDescription: String?
    val onNavigationIconClick: (() -> Unit)?
    val title: String
    val actions: @Composable RowScope.() -> Unit

    companion object {
        fun findFrom(route: String?): Toolbar? {
            return Toolbar::class.sealedSubclasses.map { kClass ->
                kClass.objectInstance as Toolbar
            }.find { screen ->
                screen.route == route
            }
        }
    }
}
