package com.mba.czdan.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val title: String, val icon: ImageVector, val route: String) {
    object Home : BottomNavItem("Ana Sayfa", Icons.Default.Home, "home")
    object Calendar : BottomNavItem("Takvim", Icons.Default.CalendarToday, "calendar")
    object Flow : BottomNavItem("Flow", Icons.Default.ShowChart, "flow")
    object Transaction : BottomNavItem("Ekle/Kaldır", Icons.Default.ShoppingCart, "transaction")
    object Profile : BottomNavItem("Profil", Icons.Default.Person, "profile")
    object TransactionUpdateScreen : BottomNavItem("Transaction Update", Icons.Default.Person, "transactionUpdate")
}