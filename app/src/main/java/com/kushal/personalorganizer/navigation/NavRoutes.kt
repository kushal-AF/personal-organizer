package com.kushal.personalorganizer.navigation

sealed class NavRoutes(val route: String) {
    object Dashboard : NavRoutes("dashboard")
    object Tasks : NavRoutes("tasks")
    object Calendar : NavRoutes("calendar")
    object Stats : NavRoutes("stats")
    object More : NavRoutes("more")
}