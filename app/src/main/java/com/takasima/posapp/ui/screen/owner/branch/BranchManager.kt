package com.takasima.posapp.ui.screen.owner.branch

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.takasima.posapp.Retro
import com.takasima.posapp.UserApi
import com.takasima.posapp.ui.screen.common.product.GetMenuListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BranchManager {
    private val _branchResponse = mutableStateOf(GetBranchListResponse())

    val branchResponse: MutableState<GetBranchListResponse>
        @Composable get() = remember {
            _branchResponse
        }

    init {
        getBranch() // will be used whenever NewsManager class is initialized
    }

    private fun getBranch() {
//        val service = Api.retrofitService.getTopArticles("us", apiKey = Constant.API_KEY)
        val retro = Retro.getRetroClientInstance().create(UserApi::class.java)

        // asynchronously sent the request and notify callback of its response or if an error occured
        retro.getBranchList().enqueue(object : Callback<GetBranchListResponse> {
            override fun onResponse(
                call: Call<GetBranchListResponse>,
                response: Response<GetBranchListResponse>
            ) {
                if (response.isSuccessful) {
                    _branchResponse.value = response.body()!!
                    Log.d("news", "${_branchResponse.value}")
                } else {
                    Log.d("error", "${response.errorBody()}")

                }
            }

            override fun onFailure(call: Call<GetBranchListResponse>, t: Throwable) {
                Log.d("error", "${t.printStackTrace()}")

            }

        })
    }
}