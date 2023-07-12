package com.takasima.posapp.data.history

data class GetOrderByIdResponse(
    val success: Boolean,
    val message: String,
    val data: Invoice?
)

data class Invoice(
    val cashier: String,
    val date: String,
    val no_order: String?,
    val total_price: Int,
    val orderList: List<OrderItem>
)

data class OrderItem(
    val order_list_id: Int,
    val order_id: Int,
    val menu_id: Int,
    val nama_menu: String,
    val price: Int,
    val qty: Int,
    val created_at: String,
    val updated_at: String
)