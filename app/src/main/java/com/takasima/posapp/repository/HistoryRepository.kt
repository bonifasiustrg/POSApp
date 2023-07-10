package com.takasima.posapp.repository

import android.util.Log
import com.takasima.posapp.data.branch.Branch
import com.takasima.posapp.data.history.GetHistoryResponse
import com.takasima.posapp.data.history.HistoryData
import com.takasima.posapp.network.Retro
import com.takasima.posapp.network.UserApi

class HistoryRepository {
    private val retrofit = Retro.getRetroClientInstance()
    private val apiService = retrofit.create(UserApi::class.java)

    suspend fun fetchHistoryData(token: String): List<HistoryData> {
        Log.e("historyRepo", "tes1")

        val response = apiService.getHistory("Bearer $token")
        if (response.isSuccessful) {
            return response.body()?.data ?: emptyList()
        }
        return emptyList()
    }
}