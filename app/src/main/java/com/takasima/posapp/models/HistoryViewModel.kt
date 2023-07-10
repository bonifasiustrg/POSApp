package com.takasima.posapp.models

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
}