package com.takasima.posapp.ui.screen.common.product

import DataStoreManager
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.takasima.posapp.data.product.MenuById
import com.takasima.posapp.models.MenuViewModel
import com.takasima.posapp.ui.components.HeadingTextComponent3
import com.takasima.posapp.ui.theme.Primary
import com.takasima.posapp.ui.theme.PrimarySecond
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

    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
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

                    }
                })
            }
        }
        Button(colors = ButtonDefaults.buttonColors(containerColor = PrimarySecond),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 32.dp),onClick = {
            navController.navigate("product_edit_screen/$menuId")
        }) {
            Text(text = "Edit Menu")
        }
    }
}

@Composable
fun SelectedMenuCard(menuItem:MenuById) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)) {
        AsyncImage(
            model = menuItem.menu_image,
            contentDescription = menuItem.menu_name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(220.dp)
                .clip(RoundedCornerShape(16.dp))
                .fillMaxSize()
                .align(Alignment.CenterHorizontally)
        )
//        val base64Text = remember { mutableStateOf("") }
//        RequestContentPermission(base64Text)
//        Log.e("PDS", "base64Text: $base64Text")
//        LaunchedEffect(base64Text.value.i) {
//            storedToken?.let { menuViewModel.updateMenuImage(storedToken, menuItem.menu_id!!, base64Text.value) }
//        }
//        Log.e("PDS", "respon: ${menuViewModel.updateImageResult.value}")
////        IconButton(onClick = {
////
////        }) {
////            Icon(imageVector = Icons.Default.Edit, contentDescription = "")
////        }

        Text(text = menuItem.menu_name!!, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        Text(text = "Rp${menuItem.menu_price!!}", fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())


        HeadingTextComponent3(value = "Tipe Menu")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = when (menuItem.menu_type!!) {
            "daily_stock" -> "Stok Harian"
            "unit_stock" -> "Stok Unit"
            else -> "Stok Default"
        })
        Spacer(modifier = Modifier.height(16.dp))


        HeadingTextComponent3(value = "Stok Menu")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = menuItem.menu_qty!!)
        Spacer(modifier = Modifier.height(16.dp))

        HeadingTextComponent3(value = "Deskripsi Menu")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = menuItem.menu_description.toString())
        Spacer(modifier = Modifier.height(16.dp))

        HeadingTextComponent3(value = "Tipe Menu")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = menuItem.menu_status!!)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun SelectedMenuCardPrev() {

}