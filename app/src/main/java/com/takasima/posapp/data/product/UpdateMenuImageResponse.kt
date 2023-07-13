package com.takasima.posapp.data.product

data class UpdateMenuImageResponse(
    val success: Boolean,
    val message: String,
    val data: MenuData
)

data class MenuData(
    val menu_id: Int,
    val branch_id: String,
    val user_id: Int,
    val menu_name: String,
    val menu_price: Int,
    val menu_qty: Int,
    val menu_type: String,
    val menu_status: String,
    val menu_image: String,
    val menu_description: String,
    val created_at: String,
    val updated_at: String
)