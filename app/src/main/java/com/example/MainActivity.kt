package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.ui.theme.BPCTheme
import com.example.navigation.BPCNavGraph
import com.example.ui.components.BPCDrawer
import com.example.ui.components.BPCBottomBar
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      BPCTheme {
        val navController = rememberNavController()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        
        // Track the current route to show/hide bars
        var currentRoute by remember { mutableStateOf("login") }
        
        navController.addOnDestinationChangedListener { _, destination, _ ->
          currentRoute = destination.route ?: "login"
        }

        val showBars = currentRoute != "login" && currentRoute != "splash"

        if (showBars) {
          BPCDrawer(
            drawerState = drawerState,
            navController = navController,
            onCloseDrawer = { scope.launch { drawerState.close() } }
          ) {
            Scaffold(
              modifier = Modifier.fillMaxSize(),
              bottomBar = {
                BPCBottomBar(navController = navController)
              }
            ) { innerPadding ->
              BPCNavGraph(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                onOpenDrawer = { scope.launch { drawerState.open() } }
              )
            }
          }
        } else {
          BPCNavGraph(
            navController = navController,
            onOpenDrawer = { scope.launch { drawerState.open() } }
          )
        }
      }
    }
  }
}
