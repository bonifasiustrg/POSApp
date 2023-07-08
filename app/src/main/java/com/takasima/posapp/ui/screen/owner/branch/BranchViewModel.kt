package com.takasima.posapp.ui.screen.owner.branch

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takasima.posapp.Retro
import com.takasima.posapp.UserApi
import com.takasima.posapp.data.BranchRepository
import com.takasima.posapp.data.MenuRepository
import com.takasima.posapp.ui.screen.common.product.Menu
import kotlinx.coroutines.launch

class BranchViewModel: ViewModel() {

    val selectedBranchState = mutableStateOf<Branch?>(null)
    private val branchRepository = BranchRepository()

    private val _branchListState = mutableStateOf<List<Branch>>(emptyList())
    val branchListState: State<List<Branch>> = _branchListState

    //    2|zJsCnEDJFW0VBxmSzBxQpZdoj4zLabePylytF0Nz
    fun fetchBranchData(token: String) {
//        val token = "2|zJsCnEDJFW0VBxmSzBxQpZdoj4zLabePylytF0Nz"
        viewModelScope.launch {
            val menuList = branchRepository.fetchBranchData(token)
            _branchListState.value = menuList
            Log.e("branchVm", "tes1")
        }
    }
}