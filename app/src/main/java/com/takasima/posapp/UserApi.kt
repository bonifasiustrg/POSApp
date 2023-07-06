package com.takasima.posapp


import com.takasima.posapp.data.addmenu.CreateProductRequest
import com.takasima.posapp.data.addmenu.CreateProductResponse
import com.takasima.posapp.data.login.SignInRequest
import com.takasima.posapp.data.login.SignInResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApi {
    @POST("login")
    fun login(
        @Body userRequest: SignInRequest
    ): Call<SignInResponse>

    @POST("menu/create")
    fun createProduct(
        @Body createProductRequest: CreateProductRequest,
        @Header("Authorization") token: String // Menambahkan header Authorization
    ): Call<CreateProductResponse>

    @GET("menu/list")
    fun getProducts(
        @Header("Authorization") token: String
    ): Call<List<CreateProductResponse.Data>>
}