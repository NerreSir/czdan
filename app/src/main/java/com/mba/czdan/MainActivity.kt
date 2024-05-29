package com.mba.czdan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mba.czdan.ui.NavGraph
import com.mba.czdan.ui.components.BottomNavItem
import com.mba.czdan.ui.components.BottomNavigationBar
import com.mba.czdan.ui.components.CustomTopAppBar
import com.mba.czdan.ui.theme.CzdanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CzdanTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentPageTitle = remember(currentRoute) {
        when (currentRoute) {
            BottomNavItem.Home.route -> BottomNavItem.Home.title
            BottomNavItem.Transaction.route -> BottomNavItem.Transaction.title
            BottomNavItem.Calendar.route -> BottomNavItem.Calendar.title
            BottomNavItem.Flow.route -> BottomNavItem.Flow.title
            BottomNavItem.Profile.route -> BottomNavItem.Profile.title
            else -> "Uygulama"
        }
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(title = currentPageTitle)
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        floatingActionButton = {},
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        NavGraph(navController = navController, modifier = Modifier.padding(paddingValues))
    }
}