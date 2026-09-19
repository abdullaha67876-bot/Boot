package com.example.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BPCBottomBar(navController: NavHostController) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("Home") },
            selected = currentRoute == "dashboard",
            onClick = { navController.navigate("dashboard") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = null) },
            label = { Text("Orders") },
            selected = currentRoute == "orders",
            onClick = { navController.navigate("orders") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Chat, contentDescription = null) },
            label = { Text("Chat") },
            selected = currentRoute == "chat",
            onClick = { navController.navigate("chat") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Notifications, contentDescription = null) },
            label = { Text("Alerts") },
            selected = currentRoute == "notifications",
            onClick = { navController.navigate("notifications") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Menu, contentDescription = null) },
            label = { Text("More") },
            selected = currentRoute == "settings",
            onClick = { navController.navigate("settings") }
        )
    }
}
