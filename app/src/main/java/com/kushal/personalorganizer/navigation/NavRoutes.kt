package com.kushal.personalorganizer.navigation

sealed class NavRoutes(val route: String) {
    object Dashboard : NavRoutes("dashboard")
    object Tasks : NavRoutes("tasks")
    object ClassesWork : NavRoutes("classes_work")
    object Calendar : NavRoutes("calendar")
    object Stats : NavRoutes("stats")
    object More : NavRoutes("more")
}