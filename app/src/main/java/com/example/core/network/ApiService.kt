package com.example.core.network

import com.example.core.models.Order
import retrofit2.http.GET
import retrofit2.http.Query

interface WooCommerceService {
    @GET("wp-json/wc/v3/orders")
    suspend fun getOrders(
        @Query("status") status: String? = null,
        @Query("search") search: String? = null
    ): List<Order>
}

interface BPCPluginService {
    @GET("wp-json/bpc/v1/status")
    suspend fun getConnectionStatus(): BPCStatusResponse
}

data class BPCStatusResponse(
    val connected: Boolean,
    val message: String
)
