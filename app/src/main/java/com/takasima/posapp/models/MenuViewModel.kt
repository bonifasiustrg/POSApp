package com.takasima.posapp.models

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takasima.posapp.repository.MenuRepository
import com.takasima.posapp.data.product.Menu
import com.takasima.posapp.data.product.MenuById
import kotlinx.coroutines.launch

class MenuViewModel : ViewModel() {
    val selectedMenuState = mutableStateOf<Menu?>(null)
    val selectedMenuByIdState = mutableStateOf<MenuById?>(null)
    private val menuRepository = MenuRepository()

    private val _menuListState = mutableStateOf<List<Menu>>(emptyList())
    val menuListState: State<List<Menu>> = _menuListState

    private val _menuByIdList = mutableStateOf<List<MenuById>>(emptyList())
    val menuByIDList: State<List<MenuById>> = _menuByIdList

    fun fetchMenuData(branchId: Int, token: String) {
//        val token = "2|zJsCnEDJFW0VBxmSzBxQpZdoj4zLabePylytF0Nz"
        viewModelScope.launch {
            val menuList = menuRepository.fetchMenuData(token, branchId)
            _menuListState.value = menuList
        }
    }


    fun fetchMenuByIdList(menuIds: String/*List<Int>*/, token: String) {
        Log.e("MenuViewModel", "fetchMenuByIdList: $menuIds")
        viewModelScope.launch {
            val response = menuRepository.fetchMenuByIdData(token, menuIds)
            Log.e("MenuViewModel", "respon: $response")
            _menuByIdList.value = response
            Log.e("MenuViewModel", "_menuByIdList: $_menuByIdList")

        }
    }

    private val _deleteSuccess = MutableLiveData<Boolean>()
    val deleteSuccess: LiveData<Boolean> = _deleteSuccess

    fun deleteMenuItem(token:String, id: Int) {
        viewModelScope.launch {
            try {
                val response = menuRepository.deleteMenuItem(token, id)
                _deleteSuccess.value = response?.success
                Log.e("MenuViewModel", "deleteMenuItem: $response $id")
            } catch (e: Exception) {
                Log.e("MenuViewModel", "deleteMenuItem: ${e.message}")
            }
        }
    }

//    private val _updateImageStatus = MutableLiveData<Result<YourResponseModel>>()
//    val updateImageStatus: LiveData<Result<YourResponseModel>> get() = _updateImageStatus
//
//    fun updateImage(id: Int, base64Image: String) {
//        viewModelScope.launch {
//            try {
//                val response = repository.updateImage(id, base64Image)
//                _updateImageStatus.value = Result.success(response.body())
//            } catch (e: Exception) {
//                _updateImageStatus.value = Result.failure(e)
//            }
//        }
//    }
}