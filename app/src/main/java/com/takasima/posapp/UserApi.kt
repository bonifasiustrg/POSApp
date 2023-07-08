package com.takasima.posapp


import com.takasima.posapp.data.addmenu.CreateProductRequest
import com.takasima.posapp.data.addmenu.CreateProductResponse
import com.takasima.posapp.data.branch.CreateBranchRequest
import com.takasima.posapp.data.branch.CreateBranchResponse
import com.takasima.posapp.data.login.SignInRequest
import com.takasima.posapp.data.login.SignInResponse
import com.takasima.posapp.ui.screen.common.product.GetMenuListResponse
import com.takasima.posapp.ui.screen.owner.branch.GetBranchListResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

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
    suspend fun getMenuList(
        @Header("Authorization") token: String,
        @Query("branch_id") branchId: Int
    ): Response<GetMenuListResponse>
    @POST("branch/create")
    fun createBranch(
        @Body createProductRequest: CreateBranchRequest,
        @Header("Authorization") token: String // Menambahkan header Authorization
    ): Call<CreateBranchResponse>

//    @GET("branch/list")
//    suspend fun getBranchList(): Response<GetBranchListResponse>
    @GET("branch/list")
    fun getBranchList(): Call<GetBranchListResponse>
/*

    @GET("menu/list")
    fun getProducts(
        @Header("Authorization") token: String
    ): Call<List<CreateProductResponse.Data>>
*/

    @GET("menu/list")
    suspend fun getMenuList(): Response<GetMenuListResponse>
}