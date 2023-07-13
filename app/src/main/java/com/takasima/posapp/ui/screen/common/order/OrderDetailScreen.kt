package com.takasima.posapp.ui.screen.common.order

import android.content.Context
import android.icu.number.Scale.none
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.takasima.posapp.R
import com.takasima.posapp.data.order.CreateOrderRequest
import com.takasima.posapp.data.order.MenuItemRequest
import com.takasima.posapp.data.order.convertToMenuItemRequest
import com.takasima.posapp.data.product.MenuById
import com.takasima.posapp.models.MenuViewModel
import com.takasima.posapp.models.OrderViewModel
import com.takasima.posapp.ui.components.HeadingTextComponent3
import com.takasima.posapp.ui.components.MyTextFieldComponent
import com.takasima.posapp.ui.theme.Primary
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun OrderDetailScreen(navController: NavHostController, menuIds:String/*="[6]"*/) {
    val context = LocalContext.current
    val dataStoreManager = remember { DataStoreManager.getInstance(context) }
    val storedToken = runBlocking { dataStoreManager.getAuthToken.first() }
    val orderViewModel: OrderViewModel = viewModel()
    val scope = rememberCoroutineScope()
    val menuViewModel: MenuViewModel = viewModel()
    val tes:  List<Int> = listOf(0)
    Log.e("tes", tes.toString())
    val menuListState by menuViewModel.menuByIDList
    val needLoading = remember { mutableStateOf(false) }

    // Fetch menu data when the screen is displayed
    LaunchedEffect(Unit) {
        storedToken?.let { menuViewModel.fetchMenuByIdList(menuIds, it) }
    }
    Log.e("Product Detail Screen", "menuListState: $menuListState")
//    val listItem: List<Map<String, Int>> = emptyList()


    val listItem = remember { mutableStateOf(emptyList<Map<String, Int>>()) }
    Log.e("ear order", "${listItem.value}")
    val orderNote = remember { mutableStateOf("") }

//    var totalPriceInit = countTotalMenuPrice(menuListState)
//    Log.e("totalPriceInit", "$totalPriceInit")
//    val totalPrice = remember { mutableStateOf(countTotalMenuPrice(menuListState)) }

    Column(
        Modifier
            .padding(16.dp)
            .fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {

        Column(/*modifier = Modifier.fillMaxHeight(0.9f)*/) {

            when {
                menuListState.isEmpty() -> {
                    // Menampilkan indikator loading atau pesan lainnya saat data sedang diambil
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                    Log.e("tes", "menuListState $menuListState")
                }

                else -> {
                    val totalPriceInit = countTotalMenuPrice(menuListState, listItem)
                    Log.e("totalPriceInit", "$totalPriceInit")
                    val totalPrice = remember { mutableStateOf(countTotalMenuPrice(menuListState, listItem)) }


                    LazyColumn(Modifier.fillMaxHeight(0.7f), content = {

                        items(menuListState.size) { index ->
                            Log.e("tes", "tes2")
                            val qty = remember { mutableStateOf(1) }


                            SelectedMenuCard(menuItem = menuListState[index], listItem = listItem, qty, totalPrice, context)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    })



                    Spacer(modifier = Modifier.height(8.dp))
                    MyTextFieldComponent(labelValue = "Catatan", icon = Icons.Default.Edit,textValue = orderNote)
                    Spacer(modifier = Modifier.height(24.dp))
                    HeadingTextComponent3(value = "Ringkasan Pesanan")

                    Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Total Harga", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Rp${ totalPrice.value }", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }
                }

            }

        }
        Row(verticalAlignment = Alignment.Bottom) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    navController.navigate("order_screen")
                }) {
                Text(text = "Batal")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Primary),
                onClick = {
                    val convertListItem = convertToMenuItemRequest(listItem)
                    val request = CreateOrderRequest(
                        orderNote = "memesan semua barang",
                        listItem = convertListItem/*listOf(
                    MenuItemRequest(menuId = 4, quantity = 3),
                    MenuItemRequest(menuId = 5, quantity = 3),
                    MenuItemRequest(menuId = 6, quantity = 3)
                )*/
                    )
                    Log.e("FoodScreen", "$convertListItem")
                    Log.e("FoodScreen", "create request order")


                    if (storedToken != null ) {
                        orderViewModel.createOrder(request, storedToken)
                        Log.e("FoodScreen", "order process in viewmodel")

                        Toast.makeText(context, "Pesanan berhasil dibuat", Toast.LENGTH_SHORT).show()

                        scope.launch {
                            delay(1000L)
                            Log.e("after create order", "order id -> ${orderViewModel.response.value}")
                            val orderId = orderViewModel.response.value?.body()?.data?.orderId
                            Log.e("FoodScreen", "order id -> $orderId")

                            if (orderId != null) {
                                Log.e("otw invoice", "order id is not null")
                                navController.navigate("invoice_screen/${orderId}")
                            } else {
                                Log.e("otw invoice", "order id is null")
                            }
                        }
                    } else {
                        Toast.makeText(context, "Pesanan gagal dibuat", Toast.LENGTH_SHORT).show()
                        Log.e("FoodScreen", "Token is null")
                    }
                }) {
                Text(text = "Selesai")
            }
        }
    }
}

@Composable
fun SelectedMenuCard(menuItem: MenuById,
                     listItem: MutableState<List<Map<String, Int>>>,
                     qty: MutableState<Int>,
                     totalPrice: MutableState<Int>,
                     context: Context

) {
    val tempItemTotalPrice = remember { mutableStateOf(menuItem.menu_price!!.toInt() * qty.value) }

    Row(Modifier.fillMaxHeight()) {
        AsyncImage(
            model = menuItem.menu_image,
            contentDescription = menuItem.menu_name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .clip(
                    RoundedCornerShape(16.dp)
                )
        )
        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)) {
            Text(text = menuItem.menu_name!!, fontWeight = FontWeight.Bold)
//            Text(text = "Rp${menuItem.menu_price!!.toInt() * qty.value}")
            Text(text = "Rp${tempItemTotalPrice.value}")
        }
        Spacer(modifier = Modifier.width(16.dp))

        Row(horizontalArrangement = Arrangement.End) {
            IconButton(onClick = {
                if (menuItem.menu_qty!!.toInt() == 1) {
                    Toast.makeText(context, "Jumlah item minimal 1", Toast.LENGTH_SHORT).show()
                } else if  (qty.value > 1 && qty.value <= menuItem.menu_qty.toInt()) {
                    qty.value--
                    totalPrice.value = totalPrice.value - tempItemTotalPrice.value
                    tempItemTotalPrice.value = menuItem.menu_price!!.toInt() * qty.value
                    totalPrice.value = totalPrice.value + tempItemTotalPrice.value

//                totalPrice.value = menuItem.menu_price!!.toInt() * qty.value
                    addItemOrUpdateQty(menuItem.menu_id!!, qty.value, listItem)
                }
            }) {
                Icon(painter = painterResource(id = R.drawable.minus_ic), contentDescription = "")
            }
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Primary),
                onClick = {
                }) {
                Text(text = qty.value.toString())
            }
            IconButton(
                onClick = {
                    if (menuItem.menu_qty!!.toInt() == 0) {
                        Toast.makeText(context, "Stok Habis", Toast.LENGTH_SHORT).show()
                    }else if (qty.value >= 0 && qty.value < menuItem.menu_qty!!.toInt()) {
                        qty.value++
                        totalPrice.value = totalPrice.value - tempItemTotalPrice.value
                        tempItemTotalPrice.value = menuItem.menu_price!!.toInt() * qty.value
                        totalPrice.value = totalPrice.value + tempItemTotalPrice.value

                    } else {
                        Toast.makeText(context, "Stok tidak mencukupi", Toast.LENGTH_SHORT).show()
                    }
//            totalPrice.value = menuItem.menu_price!!.toInt() * qty.value
                    addItemOrUpdateQty(menuItem.menu_id!!, qty.value, listItem)

                }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
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
fun countTotalMenuPrice(menuList: List<MenuById>, listItem: MutableState<List<Map<String, Int>>>): Int {
    var totalPrice = 0
    for (menu in menuList) {
        totalPrice += menu.menu_price?.toInt() ?: 0
        addItemOrUpdateQty(menu.menu_id!!, 1, listItem)
    }
    return totalPrice
}
@Composable
fun DelayComposable() {
    LaunchedEffect(key1 = Unit) {
        delay(1000)
    }
}