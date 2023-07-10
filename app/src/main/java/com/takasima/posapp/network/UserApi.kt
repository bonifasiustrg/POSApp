package com.takasima.posapp.network


import com.takasima.posapp.data.product.CreateProductRequest
import com.takasima.posapp.data.product.CreateProductResponse
import com.takasima.posapp.data.branch.CreateBranchRequest
import com.takasima.posapp.data.branch.CreateBranchResponse
import com.takasima.posapp.data.login.SignInRequest
import com.takasima.posapp.data.login.SignInResponse
import com.takasima.posapp.data.order.CreateOrderRequest
import com.takasima.posapp.data.order.CreateOrderResponse
import com.takasima.posapp.data.product.GetMenuByIdList
import com.takasima.posapp.data.product.GetMenuListResponse
import com.takasima.posapp.data.branch.GetBranchListResponse
import com.takasima.posapp.data.history.GetHistoryResponse
import com.takasima.posapp.data.product.DeleteProductResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
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

    @POST("menu/delete/2")
    suspend fun deleteMenuItem(
        @Header("Authorization") token: String,
//        @Path("id") id: Int
    ): Response<DeleteProductResponse>

    @GET("menu/list")
    suspend fun getMenuList(
        @Header("Authorization") token: String,
        @Query("branch_id") branchId: Int
    ): Response<GetMenuListResponse>
    @GET("menu/list")
    suspend fun getMenuByIdList(
        @Header("Authorization") token: String,
        @Query("menu_id") menuIds: String/*List<Int>*/)
    : Response<GetMenuByIdList>

    @POST("branch/create")
    fun createBranch(
        @Body createProductRequest: CreateBranchRequest,
        @Header("Authorization") token: String // Menambahkan header Authorization
    ): Call<CreateBranchResponse>

    @GET("branch/list")
    suspend fun getBranchList(
        @Header("Authorization") token: String
    ): Response<GetBranchListResponse>

    /*@POST("order/create")
    fun createOrder(
        @Body createOrderRequest: CreateOrderRequest,
        @Header("Authorization") token: String // Menambahkan header Authorization
    ): Call<CreateBranchResponse>*/
    @POST("order/create")
    suspend fun createOrder(
        @Body createOrderRequest: CreateOrderRequest,
        @Header("Authorization") token: String // Adding Authorization header
    ): Response<CreateOrderResponse>
//    @POST("menu/update-image/{id}")
//    suspend fun updateImage(@Path("id") id: Int, @Body requestBody: RequestBody): Response<YourResponseModel>
    @GET("order/list")
    suspend fun getHistory(
        @Header("Authorization") token: String
    ): Response<GetHistoryResponse>


}
