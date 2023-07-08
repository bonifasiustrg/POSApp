package com.takasima.posapp.data

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takasima.posapp.data.MenuRepository
import com.takasima.posapp.ui.screen.common.product.Menu
import kotlinx.coroutines.launch

class MenuViewModel : ViewModel() {
    private val menuRepository = MenuRepository()

    private val _menuListState = mutableStateOf<List<Menu>>(emptyList())
    val menuListState: State<List<Menu>> = _menuListState

    //    2|zJsCnEDJFW0VBxmSzBxQpZdoj4zLabePylytF0Nz
    fun fetchMenuData(branchId: Int) {
        val token = "2|zJsCnEDJFW0VBxmSzBxQpZdoj4zLabePylytF0Nz"
        viewModelScope.launch {
            val menuList = menuRepository.fetchMenuData(token, branchId)
            _menuListState.value = menuList
        }
    }
}