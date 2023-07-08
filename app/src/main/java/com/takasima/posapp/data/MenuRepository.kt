package com.takasima.posapp.data

import androidx.compose.runtime.mutableStateListOf
import com.takasima.posapp.Retro
import com.takasima.posapp.UserApi
import com.takasima.posapp.ui.screen.common.product.Menu

class MenuRepository {
//    val menuListState = mutableStateListOf<Menu>()
//
//    suspend fun fetchMenuData() {
//        val retrofit = Retro.getRetroClientInstance()
//        val apiService = retrofit.create(UserApi::class.java)
//        val menuResponse = apiService.getMenuList()
//
//        if (menuResponse.isSuccessful) {
//            menuListState.clear()
//            menuResponse.body()?.let { menuListState.addAll(it.data) }
//        }
//    }
    private val retrofit = Retro.getRetroClientInstance()
    private val apiService = retrofit.create(UserApi::class.java)

    suspend fun fetchMenuData(token: String, branchId: Int): List<Menu> {
        val response = apiService.getMenuList("Bearer $token", branchId)
        if (response.isSuccessful) {
            return response.body()?.data ?: emptyList()
        }
        return emptyList()
    }
}
