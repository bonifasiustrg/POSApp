package com.takasima.posapp.models

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takasima.posapp.repository.BranchRepository
import com.takasima.posapp.data.branch.Branch
import com.takasima.posapp.data.branch.GetStaffResponse
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

    private val _staffList = mutableStateOf<GetStaffResponse?>(null)
    val staffList: State<GetStaffResponse?> = _staffList

    fun getStaffList(token:String, branchId: Int) {
        viewModelScope.launch {
            try {
                val list = branchRepository.getStaffList(token ,branchId)
                Log.e("branchVm", "loadStaffList: ${list.body()}")
                _staffList.value = list.body()
            } catch (e: Exception) {
                Log.e("branchVm", "loadStaffList: ${e.message}")
            }
        }
    }
}