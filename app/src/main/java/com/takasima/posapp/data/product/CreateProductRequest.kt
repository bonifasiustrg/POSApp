package com.takasima.posapp.data.product

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CreateProductRequest {
    @SerializedName("branch_id") @Expose
    var branchId: String? = null

    @SerializedName("menu_type") @Expose
    var menuType: String? = null

    @SerializedName("menu_name") @Expose
    var menuName: String? = null

    @SerializedName("menu_price") @Expose
    var menuPrice: String? = null

    @SerializedName("menu_description") @Expose
    var menuDescription: String? = null

    @SerializedName("menu_qty") @Expose
    var menuQty: String? = null

    @SerializedName("menu_image") @Expose
    var menuImage: String? = null
}