package com.example.todo_app_mvvm_with_clean_architectrue.ui.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

/** 特定のnav_graph単位で存続するようなViewModelを生成する */
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavHostController): T {

    // 現在のnav_graphのparent.route
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()

    // 現在のnav_graphに紐づくNavBackStackEntryを取得する
    val parentEntry = remember(this) {

        // NavBackStackEntryが変化する度に実行
        navController.getBackStackEntry(navGraphRoute)
    }

    // NavBackStackEntryをViewModelStoreOwnerとするViewModelを生成
    return hiltViewModel(parentEntry)
}
