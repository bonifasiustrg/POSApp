package com.takasima.posapp.ui.screen.common.product

data class GetMenuListResponse(
    val success: Boolean,
    val message: String,
    val data: List<Menu>
)

data class Menu(
    // Definisikan atribut-atribut menu yang sesuai dengan struktur JSON
    val branch_id: String,
    val user_id: Int,
    val menu_name: String,
    val menu_qty: String,
    val menu_type: String,
    val menu_price: String,
    val menu_description: String,
    val menu_image: String?,
    val menu_status: String,
    val updated_at: String,
    val created_at: String,
    val menu_id: Int
)