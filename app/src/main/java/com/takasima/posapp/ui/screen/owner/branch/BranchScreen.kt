package com.takasima.posapp.ui.screen.owner.branch

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.takasima.posapp.R
import com.takasima.posapp.Retro
import com.takasima.posapp.UserApi
import com.takasima.posapp.data.MenuViewModel
import com.takasima.posapp.ui.components.ProductImageCard2
import com.takasima.posapp.ui.theme.Neutral
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

@Composable
fun ShopBranchScreen(navController: NavController) {
    val ctx = LocalContext.current
    val dataStoreManager = DataStoreManager.getInstance(ctx)
    val storedToken = runBlocking { dataStoreManager.getAuthToken.first() }
    val viewModel: BranchViewModel = viewModel()
    val branchListState by viewModel.branchListState

    // Fetch menu data when the screen is displayed
    LaunchedEffect(Unit) {
        storedToken?.let { viewModel.fetchBranchData(it) }
    }

    Column(Modifier.fillMaxSize()) {
        Log.e("branch", "tes1")

        Button(onClick = {
            navController.navigate("branch_add_screen")
        }) {
            Text("Add Branch")
        }

        when {
                branchListState.isEmpty() -> {
                    Log.e("branch", "tes2")
                    // Menampilkan indikator loading atau pesan lainnya saat data sedang diambil
                Text(text = "Loading...")

                }

            else -> {
                Log.e("branch", "tes3")

                LazyColumn(modifier = Modifier.padding(horizontal = 4.dp),
                    content =  {
                        items(branchListState.size) { index ->
                            Box(modifier = Modifier.padding(8.dp)) {
                                BranchItem(branchListState[index])
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }

                )
            }
        }
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