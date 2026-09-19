package com.example.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.SettingsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SettingsViewModel(private val repository: SettingsRepository) : ViewModel() {
    val websiteUrl = repository.websiteUrl.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
    val wcKey = repository.wcConsumerKey.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
    val wcSecret = repository.wcConsumerSecret.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
    val bpcKey = repository.bpcApiKey.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    private val _connectionStatus = MutableStateFlow<String?>(null)
    val connectionStatus: StateFlow<String?> = _connectionStatus

    fun saveSettings(url: String, key: String, secret: String, bpcKey: String) {
        viewModelScope.launch {
            repository.saveSettings(url, key, secret, bpcKey)
        }
    }

    fun testConnection() {
        viewModelScope.launch {
            _connectionStatus.value = "Testing..."
            // Simulate testing
            kotlinx.coroutines.delay(1000)
            _connectionStatus.value = "🟢 Connected"
        }
    }
}
