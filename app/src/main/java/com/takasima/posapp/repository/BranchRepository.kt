package com.takasima.posapp.repository

import android.util.Log
import com.takasima.posapp.network.Retro
import com.takasima.posapp.network.UserApi
import com.takasima.posapp.ui.screen.owner.branch.Branch

class BranchRepository {
    private val retrofit = Retro.getRetroClientInstance()
    private val apiService = retrofit.create(UserApi::class.java)

    suspend fun fetchBranchData(token: String="2|zJsCnEDJFW0VBxmSzBxQpZdoj4zLabePylytF0Nz"): List<Branch> {
        Log.e("branchRepo", "tes1")

        val response = apiService.getBranchList("Bearer $token")
        if (response.isSuccessful) {
            return response.body()?.data ?: emptyList()
        }
        return emptyList()
    }
}