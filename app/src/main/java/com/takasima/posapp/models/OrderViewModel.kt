package com.takasima.posapp.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takasima.posapp.data.order.CreateOrderRequest
import com.takasima.posapp.data.order.CreateOrderResponse
import com.takasima.posapp.network.UserApi
import com.takasima.posapp.repository.OrderRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class OrderViewModel() : ViewModel() {
    val orderRepository = OrderRepository()
    private val _response: MutableLiveData<Response<CreateOrderResponse>> = MutableLiveData()
    val response: LiveData<Response<CreateOrderResponse>> get() = _response

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> get() = _error

    fun createOrder(request: CreateOrderRequest, token: String) {
        viewModelScope.launch {
            try {
                val response = orderRepository.createOrder(request, token)
                _response.value = response
                Log.e("OrderViewModel", "createOrder: ${response.body()}")
                Log.e("OrderViewModel", "createOrder: ${response.body()?.message}")
            } catch (e: Exception) {
                _error.value = "Error: ${e.message}"
            }
        }
    }
}