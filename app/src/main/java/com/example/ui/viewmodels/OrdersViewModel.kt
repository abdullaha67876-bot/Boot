package com.example.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.models.Order
import com.example.core.network.NetworkModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class OrdersUiState {
    object Loading : OrdersUiState()
    data class Success(val orders: List<Order>) : OrdersUiState()
    data class Error(val message: String) : OrdersUiState()
}

class OrdersViewModel(
    private val websiteUrl: String,
    private val key: String,
    private val secret: String
) : ViewModel() {
    private val _uiState = MutableStateFlow<OrdersUiState>(OrdersUiState.Loading)
    val uiState: StateFlow<OrdersUiState> = _uiState

    init {
        fetchOrders()
    }

    fun fetchOrders() {
        viewModelScope.launch {
            _uiState.value = OrdersUiState.Loading
            try {
                val service = NetworkModule(websiteUrl, key, secret).woocommerceService
                val orders = service.getOrders()
                _uiState.value = OrdersUiState.Success(orders)
            } catch (e: Exception) {
                _uiState.value = OrdersUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
