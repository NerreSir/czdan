package com.mba.czdan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mba.czdan.ui.theme.BlueGradientEnd
import com.mba.czdan.ui.theme.CzdanTheme
import com.mba.czdan.ui.theme.PurpleGradientStart

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CzdanTheme {
                AppContent()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent() {
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
            CustomTopAppBar(currentPageTitle)
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->
        NavigationGraph(navController, modifier = Modifier.padding(paddingValues))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(title: String) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(PurpleGradientStart, BlueGradientEnd)
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.White
        )
    )
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Calendar,
        BottomNavItem.Flow,
        BottomNavItem.Home,
        BottomNavItem.Transaction,
        BottomNavItem.Profile
    )

    NavigationBar(
        containerColor = Color.Transparent,
        modifier = Modifier.background(
            brush = Brush.horizontalGradient(
                colors = listOf(PurpleGradientStart, BlueGradientEnd)
            )
        )
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = BottomNavItem.Home.route, modifier = modifier) {
        composable(BottomNavItem.Home.route) {
            HomePage()
        }
        composable(BottomNavItem.Calendar.route) {
            CalendarPage()
        }
        composable(BottomNavItem.Flow.route) {
            FlowPage()
        }
        composable(BottomNavItem.Profile.route) {
            ProfilePage()
        }
        composable(BottomNavItem.Transaction.route) {
            TransactionPage()
        }
    }
}

@Composable
fun HomePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Home Page", style = MaterialTheme.typography.headlineSmall)
    }
}

@Composable
fun CalendarPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Calendar Page", style = MaterialTheme.typography.headlineSmall)
    }
}

@Composable
fun FlowPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Flow Page", style = MaterialTheme.typography.headlineSmall)
    }
}

@Composable
fun ProfilePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Profile Page", style = MaterialTheme.typography.headlineSmall)
    }
}

@Composable
fun TransactionPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Transaction Page", style = MaterialTheme.typography.headlineSmall)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CzdanTheme {
        AppContent()
    }
}
