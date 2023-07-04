package com.takasima.posapp


import com.example.loginflow.data.SignInRequest
import com.example.loginflow.data.SignInResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("login")
    fun login(
        @Body userRequest: SignInRequest
    ): Call<SignInResponse>

}