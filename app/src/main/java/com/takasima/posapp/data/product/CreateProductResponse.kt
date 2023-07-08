package com.takasima.posapp.data.product

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CreateProductResponse {
    @SerializedName("success")   @Expose
    var success: Boolean? = null
    @SerializedName("message")   @Expose
    var message: String? = null

    @SerializedName("data")   @Expose
    var data: Data? = null

    class Data {
        @SerializedName("branch_id")   @Expose
        var branch_id: String? = null

        @SerializedName("user_id")   @Expose
        var user_id: String? = null

        @SerializedName("menu_name")   @Expose
        var menu_name: String? = null

        @SerializedName("menu_qty")   @Expose
        var menu_qty: String? = null

        @SerializedName("menu_type")   @Expose
        var menu_type: String? = null

        @SerializedName("menu_price")   @Expose
        var menu_price: String? = null
        @SerializedName("menu_description")   @Expose
        var menu_description: String? = null
        @SerializedName("menu_image")   @Expose
        var menu_image: String? = null
        @SerializedName("menu_status")   @Expose
        var menu_status: String? = null

    }
}