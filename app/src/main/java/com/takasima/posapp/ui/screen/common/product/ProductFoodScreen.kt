package com.takasima.posapp.ui.screen.common.product

import DataStoreManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.takasima.posapp.models.MenuViewModel
import com.takasima.posapp.ui.components.ProductImageCard2
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@Composable
fun ProductFoodScreen(navController: NavHostController, branchId: Int=1) {
    val ctx = LocalContext.current
    val dataStoreManager = DataStoreManager.getInstance(ctx)
    val storedToken = runBlocking { dataStoreManager.getAuthToken.first() }
    val viewModel: MenuViewModel = viewModel()
    val menuListState by viewModel.menuListState

    // Fetch menu data when the screen is displayed
    LaunchedEffect(Unit) {
        storedToken?.let { viewModel.fetchMenuData(branchId, it) }
    }

    Column {
        when {
            menuListState.isEmpty() -> {
                // Menampilkan indikator loading atau pesan lainnya saat data sedang diambil
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
    
            else -> {
                LazyVerticalGrid(modifier = Modifier.padding(horizontal = 4.dp),
                    columns = GridCells.Fixed(2),
                    content =  {
                        items(menuListState.size) { index ->
                            Box(modifier = Modifier.padding(8.dp)) {
                                ProductImageCard2(/*imgRes =R.drawable.food, */menuListState[index], navController, viewModel)
                            }
                        }
                    }

                )
            }
        }
    }
}