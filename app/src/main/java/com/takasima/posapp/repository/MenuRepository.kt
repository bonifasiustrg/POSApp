package com.takasima.posapp.repository

import android.util.Log
import com.takasima.posapp.data.product.DeleteProductResponse
import com.takasima.posapp.network.Retro
import com.takasima.posapp.network.UserApi
import com.takasima.posapp.data.product.Menu
import com.takasima.posapp.data.product.MenuById
import com.takasima.posapp.data.product.UpdateMenuImageResponse
import retrofit2.Response

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
    suspend fun fetchMenuByIdData(token: String, menuIds: String/*List<Int>*/): List<MenuById> {
        Log.e("Repo", "fetchMenuByIdList: $menuIds")

        val response = apiService.getMenuByIdList("Bearer $token", menuIds)
        Log.e("MenuViewModel", "respon: $response")

        if (response.isSuccessful) {
            return response.body()?.data ?: emptyList()
        }
        return emptyList()
    }

    suspend fun deleteMenuItem(token:String, menuId: Int): Response<DeleteProductResponse> {
        return apiService.deleteMenuItem(menuId,"Bearer $token")
    }

//    suspend fun updateMenuImage(token:String, id: Int, image: String): Response<UpdateMenuImageResponse> {
//        val response = apiService.updateMenuImage(token ,id, image)
//        Log.e("MenuRepository", "updateMenuImage: $response")
//        if (response.isSuccessful) {
//            return response
//        } else {
//            throw Exception("Error: ${response.code()}")
//        }
//    }
}
