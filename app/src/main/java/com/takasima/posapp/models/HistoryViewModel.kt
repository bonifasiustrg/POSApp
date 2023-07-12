package com.takasima.posapp.models

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takasima.posapp.data.history.GetOrderByIdResponse
import com.takasima.posapp.data.history.HistoryData
import com.takasima.posapp.repository.HistoryRepository
import kotlinx.coroutines.launch

class HistoryViewModel: ViewModel() {


    val selectedHistoryState = mutableStateOf<HistoryData?>(null)
    private val historyRepository = HistoryRepository()

    private val _historyListState = mutableStateOf<List<HistoryData>>(emptyList())
    val historyListState: State<List<HistoryData>> = _historyListState


    fun fetchHistoryData(token: String) {
        viewModelScope.launch {
            val menuList = historyRepository.fetchHistoryData(token)
            _historyListState.value = menuList
            Log.e("History View Model", "tes1")
        }
    }

    private val _orderDataState = mutableStateOf<GetOrderByIdResponse?>(null)
    val orderDataState: State<GetOrderByIdResponse?> = _orderDataState

    fun getOrderById(token: String, orderId: Int) {
        viewModelScope.launch {
            try {
                val response = historyRepository.getOrderById(token, orderId)
                Log.e("HistoryViewModel", "response: $response")
                _orderDataState.value = response
            } catch (e: Exception) {
                Log.e("HistoryViewModel", "Error: ${e.message}")
            }
        }
    }

}