package com.example.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.BPCApplication
import com.example.ui.viewmodels.SettingsViewModel
import com.example.ui.viewmodels.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onOpenDrawer: () -> Unit) {
    val context = LocalContext.current
    val app = context.applicationContext as BPCApplication
    val viewModel: SettingsViewModel = viewModel(factory = ViewModelFactory(app.settingsRepository))
    
    val savedUrl by viewModel.websiteUrl.collectAsState()
    val savedKey by viewModel.wcKey.collectAsState()
    val savedSecret by viewModel.wcSecret.collectAsState()
    val savedBpcKey by viewModel.bpcKey.collectAsState()
    val status by viewModel.connectionStatus.collectAsState()

    var websiteUrl by remember { mutableStateOf("") }
    var wcKey by remember { mutableStateOf("") }
    var wcSecret by remember { mutableStateOf("") }
    var bpcKey by remember { mutableStateOf("") }

    LaunchedEffect(savedUrl) { websiteUrl = savedUrl ?: "" }
    LaunchedEffect(savedKey) { wcKey = savedKey ?: "" }
    LaunchedEffect(savedSecret) { wcSecret = savedSecret ?: "" }
    LaunchedEffect(savedBpcKey) { bpcKey = savedBpcKey ?: "" }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onOpenDrawer) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text("Website & API Settings", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
            
            OutlinedTextField(
                value = websiteUrl,
                onValueChange = { websiteUrl = it },
                label = { Text("Website URL") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = wcKey,
                onValueChange = { wcKey = it },
                label = { Text("WooCommerce Consumer Key") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = wcSecret,
                onValueChange = { wcSecret = it },
                label = { Text("WooCommerce Consumer Secret") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = bpcKey,
                onValueChange = { bpcKey = it },
                label = { Text("BPC API Key") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            
            status?.let {
                Text(it, color = if (it.contains("🟢")) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error)
                Spacer(modifier = Modifier.height(8.dp))
            }

            Button(onClick = { viewModel.testConnection() }, modifier = Modifier.fillMaxWidth()) {
                Text("Test Connection")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { viewModel.saveSettings(websiteUrl, wcKey, wcSecret, bpcKey) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Settings")
            }
        }
    }
}
