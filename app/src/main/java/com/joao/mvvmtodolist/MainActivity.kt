package com.joao.mvvmtodolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.joao.mvvmtodolist.ui.add_edit_todo.AddEditTodoScreen
import com.joao.mvvmtodolist.ui.theme.MVVMTodoListTheme
import com.joao.mvvmtodolist.ui.todo_list.TodoListScreen
import com.joao.mvvmtodolist.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Routes.TODO_LIST
            ) {
                composable(Routes.TODO_LIST) {
                    TodoListScreen(
                        onNavigate = {
                            navController.navigate(it.route)
                        }
                    )
                }
                composable(
                    Routes.ADD_EDIT_TODO + "?todoId={todoId}",
                    arguments = listOf(
                        navArgument(name = "todoId") {
                            type = NavType.IntType
                            defaultValue = -1
                        }
                    )
                ) {
                      AddEditTodoScreen(onPopBackStack = {
                          navController.popBackStack()
                      })
                }
            }
        }
    }
}