package com.takasima.posapp.data.branch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CreateBranchResponse {

    @SerializedName("success")   @Expose
    var success: Boolean? = null
    @SerializedName("message")   @Expose
    var message: String? = null

    @SerializedName("data")   @Expose
    var data: Data? = null

    class Data {
//        @SerializedName("branch_id")   @Expose
//        var branch_id: String? = null

        @SerializedName("branch_name")   @Expose
        var branch_name: String? = null

        @SerializedName("branch_address")   @Expose
        var branch_address: String? = null

        @SerializedName("branch_phone")   @Expose
        var branch_phone: String? = null

        @SerializedName("branch_email")   @Expose
        var branch_email: String? = null

        @SerializedName("branch_status")   @Expose
        var branch_status: String? = null
    }
}