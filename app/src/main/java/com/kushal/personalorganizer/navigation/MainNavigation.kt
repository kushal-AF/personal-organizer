package com.kushal.personalorganizer.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.kushal.personalorganizer.feature.classeswork.ClassWorkScreen
import com.kushal.personalorganizer.feature.dashboard.CalendarScreen
import com.kushal.personalorganizer.feature.dashboard.DashboardScreen
import com.kushal.personalorganizer.feature.dashboard.MoreScreen
import com.kushal.personalorganizer.feature.dashboard.StatsScreen
import com.kushal.personalorganizer.feature.tasks.TasksScreen
import com.kushal.personalorganizer.ui.theme.AccentTasks
import com.kushal.personalorganizer.ui.theme.CardBackground

private data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

private val bottomNavItems = listOf(
    BottomNavItem(NavRoutes.Dashboard.route, "Home", Icons.Default.Home),
    BottomNavItem(NavRoutes.Calendar.route, "Calendar", Icons.Default.CalendarMonth),
    BottomNavItem(NavRoutes.Stats.route, "Stats", Icons.Default.BarChart),
    BottomNavItem(NavRoutes.More.route, "More", Icons.Default.MoreHoriz)
)

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    Scaffold(
        containerColor = Color(0xFF121212),
        bottomBar = {
            NavigationBar(containerColor = CardBackground) {
                val currentEntry by navController.currentBackStackEntryAsState()
                val currentRoute = currentEntry?.destination?.route

                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = AccentTasks,
                            selectedTextColor = AccentTasks,
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray,
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = NavRoutes.Dashboard.route,
            modifier = androidx.compose.ui.Modifier.padding(padding)
        ) {
            composable(NavRoutes.Dashboard.route) { DashboardScreen(navController = navController) }
            composable(NavRoutes.Tasks.route) { TasksScreen(onBack = { navController.popBackStack() }) }
            composable(NavRoutes.ClassesWork.route) { ClassWorkScreen(onBack = { navController.popBackStack() }) }
            composable(NavRoutes.Calendar.route) { CalendarScreen() }
            composable(NavRoutes.Stats.route) { StatsScreen() }
            composable(NavRoutes.More.route) { MoreScreen() }
        }
    }
}