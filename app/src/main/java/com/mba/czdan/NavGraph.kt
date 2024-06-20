package com.mba.czdan

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mba.czdan.ui.components.BottomNavItem
import com.mba.czdan.ui.screens.CalendarScreen
import com.mba.czdan.ui.screens.FlowScreen
import com.mba.czdan.ui.screens.HomeScreen
import com.mba.czdan.ui.screens.ProfileScreen
import com.mba.czdan.ui.screens.TransactionScreen
import com.mba.czdan.ui.screens.TransactionUpdateScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            HomeScreen(navController)
        }
        composable(BottomNavItem.Calendar.route) {
            CalendarScreen()
        }
        composable(BottomNavItem.Flow.route) {
            FlowScreen(navController)
        }
        composable(BottomNavItem.Transaction.route) {
            TransactionScreen()
        }
        composable(BottomNavItem.Profile.route) {
            ProfileScreen()
        }

        composable("transactionUpdate/{transactionUpdate}") { backStackEntry ->
            val transactionId = backStackEntry.arguments?.getString("transactionUpdate")
            if (transactionId != null) {
                TransactionUpdateScreen(transactionEntity = transactionId,navController)
            }
        }

    }
}