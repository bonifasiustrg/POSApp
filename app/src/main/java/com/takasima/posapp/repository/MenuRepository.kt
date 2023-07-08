package com.takasima.posapp.repository

import com.takasima.posapp.network.Retro
import com.takasima.posapp.network.UserApi
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
