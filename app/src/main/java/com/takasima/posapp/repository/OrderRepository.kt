package com.takasima.posapp.repository

import com.takasima.posapp.data.order.CreateOrderRequest
import com.takasima.posapp.data.order.CreateOrderResponse
import com.takasima.posapp.network.Retro
import com.takasima.posapp.network.UserApi
import retrofit2.Response

class OrderRepository {

    private val retrofit = Retro.getRetroClientInstance()
    private val apiService = retrofit.create(UserApi::class.java)

    suspend fun createOrder(createOrderRequest: CreateOrderRequest, token: String): Response<CreateOrderResponse> {
        return apiService.createOrder(createOrderRequest, "Bearer $token")
    }

}