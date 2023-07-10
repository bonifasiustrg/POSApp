package com.takasima.posapp.data.order

import androidx.compose.runtime.MutableState
import com.google.gson.annotations.SerializedName

data class CreateOrderRequest(
    @SerializedName("order_note")
    val orderNote: String,

    @SerializedName("listItem")
    val listItem: List<MenuItemRequest>
)

data class MenuItemRequest(
    @SerializedName("menu_id") val menuId: Int,
    @SerializedName("qty") val quantity: Int
)

fun convertToMenuItemRequest(listItem: MutableState<List<Map<String, Int>>>): List<MenuItemRequest> {
    return listItem.value.map { map ->
        val menuId = map["menu_id"] ?: throw IllegalArgumentException("menu_id tidak ditemukan")
        val quantity = map["qty"] ?: throw IllegalArgumentException("qty tidak ditemukan")
        MenuItemRequest(menuId, quantity)
    }
}