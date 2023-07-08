package com.takasima.posapp.data

import android.util.Log
import com.takasima.posapp.Retro
import com.takasima.posapp.UserApi
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