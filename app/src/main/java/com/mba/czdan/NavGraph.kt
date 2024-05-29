package com.mba.czdan

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mba.czdan.ui.components.BottomNavItem
import com.mba.czdan.ui.screen.HomeScreen
import com.mba.czdan.ui.screens.CalendarScreen
import com.mba.czdan.ui.screens.FlowScreen
import com.mba.czdan.ui.screens.ProfileScreen
import com.mba.czdan.ui.screens.TransactionScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            HomeScreen()
        }
        composable(BottomNavItem.Calendar.route) {
            CalendarScreen()
        }
        composable(BottomNavItem.Flow.route) {
            FlowScreen()
        }
        composable(BottomNavItem.Transaction.route) {
            TransactionScreen()
        }
        composable(BottomNavItem.Profile.route) {
            ProfileScreen()
        }
    }
}