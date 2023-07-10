package com.takasima.posapp.data.order

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CreateOrderResponse(
    val success: Boolean,
    val message: String,
    val data: ResponseData
)

data class ResponseData(
    @SerializedName("branch_id")
    val branchId: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("order_note")
    val orderNote: String,
    @SerializedName("order_status")
    val orderStatus: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("order_id")
    val orderId: Int
)