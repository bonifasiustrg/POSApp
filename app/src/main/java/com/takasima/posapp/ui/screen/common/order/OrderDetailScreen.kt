package com.takasima.posapp.ui.screen.common.order

import android.icu.number.Scale.none
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.takasima.posapp.data.product.MenuById
import com.takasima.posapp.models.MenuViewModel
import com.takasima.posapp.ui.components.MyTextFieldComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@Composable
fun OrderDetailScreen(navController: NavHostController, menuIds:String="[6]") {
    val context = LocalContext.current
    val dataStoreManager = remember { DataStoreManager.getInstance(context) }
    val storedToken = runBlocking { dataStoreManager.getAuthToken.first() }

    val menuViewModel: MenuViewModel = viewModel()
    val tes:  List<Int> = listOf(0)
    Log.e("tes", tes.toString())
    val menuListState by menuViewModel.menuByIDList

    // Fetch menu data when the screen is displayed
    LaunchedEffect(Unit) {
        storedToken?.let { menuViewModel.fetchMenuByIdList(menuIds, it) }
    }
    Log.e("Product Detail Screen", "menuListState: $menuListState")
    Text(text = "Order Detail Screen")
//    val listItem: List<Map<String, Int>> = emptyList()
    val listItem = remember { mutableStateOf(emptyList<Map<String, Int>>()) }
    Column(Modifier.padding(16.dp)) {
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
                        var qty = remember { mutableStateOf(0) }


                        SelectedMenuCard(menuItem = menuListState[index], listItem = listItem, qty)

                    }
                })
            }

        }

        val orderNote = remember { mutableStateOf("") }

        MyTextFieldComponent(labelValue = "Note", icon = Icons.Default.Edit,textValue = orderNote)
        Text(text = "Ringkasan Pesanan")

        Button(onClick = {
            Log.e("tes", "order note -> ${orderNote.value.toString()}")
            Log.e("tes", "list item -> ${ listItem.value.toString() }")
            val json = mapOf(
                "order_note" to orderNote.value,
                "listItem" to listItem.value
            )
            Log.e("tes", "json -> ${ json.toString() }")
        }) {
            Text(text = "Lihat isi pesanan")

        }
    }
}

@Composable
fun SelectedMenuCard(menuItem: MenuById,  listItem: MutableState<List<Map<String, Int>>>, qty: MutableState<Int>) {
    Row(Modifier.fillMaxHeight()) {
        AsyncImage(
            model = menuItem.menu_image,
            contentDescription = menuItem.menu_name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column() {
            Text(text = menuItem.menu_name!!)
            Text(text = menuItem.menu_price!!)
        }
        Spacer(modifier = Modifier.width(16.dp))

        IconButton(onClick = {
            if (qty.value > 0) {
                qty.value--
                addItemOrUpdateQty(menuItem.menu_id!!, qty.value, listItem)
            }
        }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
        }
        Button(onClick = {
        }) {
            Text(text = qty.value.toString())
        }
        IconButton(onClick = {
            qty.value++
            addItemOrUpdateQty(menuItem.menu_id!!, qty.value, listItem)

        }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "")
        }
    }
}
fun addItemOrUpdateQty(menuId: Int, qty: Int, listItem: MutableState<List<Map<String, Int>>>) {
    listItem.value = listItem.value.map { item ->
        if (item["menu_id"] == menuId) {
            item + ("qty" to qty)
        } else {
            item
        }
    }.toMutableList().apply {
        if (none { it["menu_id"] == menuId }) {
            add(mapOf("menu_id" to menuId, "qty" to qty))
        }
    }
}