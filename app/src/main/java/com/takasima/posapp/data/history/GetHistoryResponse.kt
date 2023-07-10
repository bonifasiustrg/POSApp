package com.takasima.posapp.data.history

data class GetHistoryResponse(
    val success: Boolean,
    val message: String,
    val data: List<HistoryData>
)
data class HistoryData(
    val order_id: Int,
    val user_id: String,
    val branch_id: String,
    val no_order: String?,
    val order_status: String,
    val order_note: String,
    val created_at: String,
    val updated_at: String,
    val order_list: List<Order>,
    val branch: Branch
)
data class Order(
    val order_list_id: Int,
    val order_id: String,
    val menu_id: String,
    val nama_menu: String,
    val price: String,
    val qty: String,
    val created_at: String,
    val updated_at: String
)
data class Branch(
    val branch_id: Int,
    val owner_id: String,
    val branch_name: String,
    val branch_address: String,
    val branch_phone: String?,
    val branch_email: String?,
    val branch_status: String?,
    val created_at: String,
    val updated_at: String
)