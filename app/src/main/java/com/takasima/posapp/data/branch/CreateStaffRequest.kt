package com.takasima.posapp.data.branch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CreateStaffRequest {
    @SerializedName("name") @Expose
    var name: String? = null

    @SerializedName("email") @Expose
    var email: String? = null

    @SerializedName("password") @Expose
    var password: String? = null

    @SerializedName("branch") @Expose
    var branch: Int? = null

    @SerializedName("no_hp") @Expose
    var no_hp: String? = null

    @SerializedName("address") @Expose
    var address: String? = null

}