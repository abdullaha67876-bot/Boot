package com.example.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ui.screens.LoginScreen
import com.example.ui.screens.DashboardScreen
import com.example.ui.screens.OrdersScreen
import com.example.ui.screens.CustomersScreen
import com.example.ui.screens.ChatScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.screens.ProductsScreen
import com.example.ui.screens.ReportsScreen
import com.example.ui.screens.AIAssistantScreen
import com.example.ui.screens.NotificationsScreen

@Composable
fun BPCNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onOpenDrawer: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = modifier
    ) {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("dashboard") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
        composable("dashboard") {
            DashboardScreen(onOpenDrawer = onOpenDrawer)
        }
        composable("orders") {
            OrdersScreen(onOpenDrawer = onOpenDrawer)
        }
        composable("customers") {
            CustomersScreen(onOpenDrawer = onOpenDrawer)
        }
        composable("chat") {
            ChatScreen(onOpenDrawer = onOpenDrawer)
        }
        composable("products") {
            ProductsScreen(onOpenDrawer = onOpenDrawer)
        }
        composable("reports") {
            ReportsScreen(onOpenDrawer = onOpenDrawer)
        }
        composable("ai_assistant") {
            AIAssistantScreen(onOpenDrawer = onOpenDrawer)
        }
        composable("notifications") {
            NotificationsScreen(onOpenDrawer = onOpenDrawer)
        }
        composable("settings") {
            SettingsScreen(onOpenDrawer = onOpenDrawer)
        }
    }
}
