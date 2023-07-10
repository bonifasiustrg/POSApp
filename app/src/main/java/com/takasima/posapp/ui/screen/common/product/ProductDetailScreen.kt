package com.takasima.posapp.ui.screen.common.product

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.takasima.posapp.R
import com.takasima.posapp.data.product.MenuById
import com.takasima.posapp.models.MenuViewModel
import com.takasima.posapp.ui.components.ProductImageCard2
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@Composable
fun ProductDetailScreen(navController: NavHostController, menuId: String) {
    val context = LocalContext.current
    val dataStoreManager = remember { DataStoreManager.getInstance(context) }
    val storedToken = runBlocking { dataStoreManager.getAuthToken.first() }

    val menuViewModel: MenuViewModel = viewModel()

    Log.e("tes", menuId)
    val menuListState by menuViewModel.menuByIDList

    // Fetch menu data when the screen is displayed
    LaunchedEffect(Unit) {
        storedToken?.let { menuViewModel.fetchMenuByIdList(menuId, it) }
    }
    Log.e("Product Detail Screen1", "menuListState: $menuListState")

    Column {
        when {
            menuListState.isEmpty() -> {
                // Menampilkan indikator loading atau pesan lainnya saat data sedang diambil
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                Log.e("tes", "menuListState $menuListState")
            }

            else -> {
                LazyColumn(content = {
                    items(menuListState.size) { index ->
                        Log.e("tes", "tes2")

                        SelectedMenuCard(menuItem = menuListState[index])
//                        Text(text = index.toString())
//                        Text(text = menuListState[index].menu_name!!)
                    }
                })
            }
        }
    }
}

@Composable
fun SelectedMenuCard(menuItem:MenuById) {
    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.Center) {
        AsyncImage(
            model = menuItem.menu_image,
            contentDescription = menuItem.menu_name,
            contentScale = ContentScale.Crop
        )
        Text(text = menuItem.menu_name!!)
        Text(text = menuItem.menu_price!!)
        Text(text = menuItem.menu_qty!!)
        Text(text = menuItem.menu_description!!)
    }
}