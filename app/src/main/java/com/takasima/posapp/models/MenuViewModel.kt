package com.takasima.posapp.models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takasima.posapp.repository.MenuRepository
import com.takasima.posapp.ui.screen.common.product.Menu
import kotlinx.coroutines.launch

class MenuViewModel : ViewModel() {
    val selectedMenuState = mutableStateOf<Menu?>(null)
    private val menuRepository = MenuRepository()

    private val _menuListState = mutableStateOf<List<Menu>>(emptyList())
    val menuListState: State<List<Menu>> = _menuListState

    //    2|zJsCnEDJFW0VBxmSzBxQpZdoj4zLabePylytF0Nz
    fun fetchMenuData(branchId: Int, token: String) {
//        val token = "2|zJsCnEDJFW0VBxmSzBxQpZdoj4zLabePylytF0Nz"
        viewModelScope.launch {
            val menuList = menuRepository.fetchMenuData(token, branchId)
            _menuListState.value = menuList
        }
    }
}