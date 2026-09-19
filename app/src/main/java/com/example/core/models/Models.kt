package com.example.core.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Order(
    val id: Int,
    val status: String,
    val currency: String,
    val total: String,
    @Json(name = "billing") val billing: Billing,
    @Json(name = "line_items") val items: List<LineItem>,
    @Json(name = "date_created") val dateCreated: String
)

@JsonClass(generateAdapter = true)
data class Billing(
    @Json(name = "first_name") val firstName: String,
    @Json(name = "last_name") val lastName: String,
    val email: String,
    val phone: String
)

@JsonClass(generateAdapter = true)
data class LineItem(
    val id: Int,
    val name: String,
    val quantity: Int,
    val total: String
)

@JsonClass(generateAdapter = true)
data class User(
    val id: String,
    val name: String,
    val email: String,
    val role: String,
    val photoUrl: String? = null
)
