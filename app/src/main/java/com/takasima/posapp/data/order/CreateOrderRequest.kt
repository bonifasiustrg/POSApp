package com.takasima.posapp.data.order

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