package com.takasima.posapp.ui.screen.owner.branch

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.takasima.posapp.Retro
import com.takasima.posapp.UserApi
import com.takasima.posapp.ui.theme.Neutral
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ShopBranchScreen(navController: NavController) {
////    var branchList by remember { mutableStateOf(emptyList<Branch>()) }
//    val retro = Retro.getRetroClientInstance().create(UserApi::class.java)
//    val branchManager= BranchManager()
////    val branchList: List<Branch> = branchManager.branchResponse.value.data
//    val branchList: List<Branch> = branchManager.branchResponse.value.data.orEmpty()
//
//    LaunchedEffect(true) {
//        val response = withContext(Dispatchers.IO) {
//            retro.getBranchList()
//        }
//        if (response.isSuccessful) {
//            val apiResponse = response.body()
//            if (apiResponse?.success == true) {
//                branchList = apiResponse.data
//            }
//        }
//        Log.e("branch", "tes1a")
//    }
//
    Column(Modifier.fillMaxSize()) {
        Log.e("branch", "tes2")

        Button(onClick = {
            navController.navigate("branch_add_screen")
        }) {
            Text("Add Branch")
        }
////        Log.e("branch", "tes3 ${branchList.size}")
//
//        LazyColumn() {
//            items(branchList.size) { index ->
////                BranchItem(branch)
//                Text(text = branchList[index].branch_name, fontWeight = FontWeight.Bold)
//                Spacer(modifier = Modifier.height(16.dp))
//            }
//        }
    }
}

@Composable
fun BranchItem(branch: Branch) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Neutral)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = branch.branch_name, fontWeight = FontWeight.Bold)
            Text(text = branch.branch_address)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ShopPrev() {
    ShopBranchScreen(rememberNavController())
}