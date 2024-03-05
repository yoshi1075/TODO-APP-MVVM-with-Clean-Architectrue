package com.example.todo_app_mvvm_with_clean_architectrue.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todo_app_mvvm_with_clean_architectrue.ui.common.ObserveAsEvent
import com.example.todo_app_mvvm_with_clean_architectrue.ui.extensions.sharedViewModel
import com.example.todo_app_mvvm_with_clean_architectrue.ui.shared.SharedOneTimeEvent
import com.example.todo_app_mvvm_with_clean_architectrue.ui.shared.SharedViewModel
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_edit.TodoEditScreen
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_edit.TodoEditViewModel
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_list.TodoListScreen
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_list.TodoListViewModel
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_register.TodoRegisterScreen
import com.example.todo_app_mvvm_with_clean_architectrue.ui.todo_register.TodoRegisterViewModel
import kotlinx.coroutines.launch

@Composable
fun AppNavHost(navController: NavHostController) {
    val snackbarHostState = remember { SnackbarHostState() }
    val hostCoroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.List.route,
            modifier = Modifier.padding(it),
        ) {
            composable(Screen.List.route) { backStackEntry ->

                // FixMe: sharedViewModelを使用し、アプリ全体の表示/動作を制御する
                val sharedViewModel = backStackEntry.sharedViewModel<SharedViewModel>(navController = navController)
                val sharedState by sharedViewModel.sharedState.collectAsStateWithLifecycle()
                val viewModel: TodoListViewModel = hiltViewModel()
                val state by viewModel.uiState.collectAsStateWithLifecycle()

                ObserveAsEvent(sharedViewModel.sharedOneTimeEvent) { oneTimeEvent ->
                    when (oneTimeEvent) {
                        SharedOneTimeEvent.Nothing -> {}

                        is SharedOneTimeEvent.ShowSnackbar -> {
                            hostCoroutineScope.launch {
                                snackbarHostState.showSnackbar(oneTimeEvent.message, duration = SnackbarDuration.Short)
                            }
                        }
                    }
                }

                TodoListScreen(
                    sharedState,
                    sharedViewModel::onEvent,
                    state,
                    viewModel::onEvent,
                    navigateToTodoRegisterScreen = { navController.navigate(Screen.Register.route) },
                    navigateToTodoEditScreen = { todoId -> navController.navigate(Screen.Edit.route + "/$todoId") },
                )
            }
            composable(Screen.Register.route) { backStackEntry ->

                // FixMe: sharedViewModelを使用し、アプリ全体の表示/動作を制御する
                val sharedViewModel = backStackEntry.sharedViewModel<SharedViewModel>(navController = navController)
                val sharedState by sharedViewModel.sharedState.collectAsStateWithLifecycle()
                val viewModel: TodoRegisterViewModel = hiltViewModel()
                val state by viewModel.uiState.collectAsStateWithLifecycle()

                ObserveAsEvent(sharedViewModel.sharedOneTimeEvent) { oneTimeEvent ->
                    when (oneTimeEvent) {
                        SharedOneTimeEvent.Nothing -> {}

                        is SharedOneTimeEvent.ShowSnackbar -> {
                            hostCoroutineScope.launch {
                                snackbarHostState.showSnackbar(oneTimeEvent.message, duration = SnackbarDuration.Short)
                            }
                        }
                    }
                }

                TodoRegisterScreen(
                    sharedState,
                    sharedViewModel::onEvent,
                    state,
                    viewModel.oneTimeEvent,
                    viewModel::onEvent,
                    backToTodoListScreen = { navController.popBackStack() },
                )
            }
            composable(
                Screen.Edit.route + "/{todoId}",
                arguments = listOf(navArgument("todoId") { type = NavType.IntType }),
            ) { backStackEntry ->

                // FixMe: sharedViewModelを使用し、アプリ全体の表示/動作を制御する
                val sharedViewModel = backStackEntry.sharedViewModel<SharedViewModel>(navController = navController)
                val sharedState by sharedViewModel.sharedState.collectAsStateWithLifecycle()
                val viewModel: TodoEditViewModel = hiltViewModel()
                val state by viewModel.uiState.collectAsStateWithLifecycle()

                ObserveAsEvent(sharedViewModel.sharedOneTimeEvent) { oneTimeEvent ->
                    when (oneTimeEvent) {
                        SharedOneTimeEvent.Nothing -> {}

                        is SharedOneTimeEvent.ShowSnackbar -> {
                            hostCoroutineScope.launch {
                                snackbarHostState.showSnackbar(oneTimeEvent.message, duration = SnackbarDuration.Short)
                            }
                        }
                    }
                }

                TodoEditScreen(
                    sharedState,
                    sharedViewModel::onEvent,
                    state,
                    viewModel::onEvent,
                    backToTodoListScreen = { navController.popBackStack() },
                )
            }
        }
    }
}
