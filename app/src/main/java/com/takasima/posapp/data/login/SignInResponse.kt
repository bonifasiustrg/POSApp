package com.takasima.posapp.data.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SignInResponse {
    @SerializedName("success")   @Expose
    var success: Boolean? = null
    @SerializedName("message")   @Expose
    var message: String? = null

    @SerializedName("data")   @Expose
    var data: Data? = null

    class Data {
//
//        @SerializedName("name")   @Expose
//        var name: String? = null

        @SerializedName("user")   @Expose
        var user: User? = null

//        @SerializedName("password")   @Expose
//        var password: String? = null
        @SerializedName("role")   @Expose
        var role: String? = null

        @SerializedName("token")   @Expose
        var token: String? = null
    }

    class User {
        @SerializedName("email")
        @Expose
        var email: String? = null
    }
}