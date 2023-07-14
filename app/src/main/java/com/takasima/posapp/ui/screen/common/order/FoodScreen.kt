package com.takasima.posapp.ui.screen.common.order

import DataStoreManager
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.takasima.posapp.models.MenuViewModel
import com.takasima.posapp.models.OrderViewModel
import com.takasima.posapp.ui.components.OrderImageCard3
import com.takasima.posapp.ui.components.ProductImageCard2
import com.takasima.posapp.ui.theme.PrimarySecond
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@Composable
fun FoodScreen(navController: NavHostController, branchId: Int=1) {
    val orderViewModel: OrderViewModel = viewModel()
    val ctx = LocalContext.current
    val dataStoreManager = DataStoreManager.getInstance(ctx)
    val storedToken = runBlocking { dataStoreManager.getAuthToken.first() }
    val viewModel: MenuViewModel = viewModel()
    val menuListState by viewModel.menuListState

    // Fetch menu data when the screen is displayed
    LaunchedEffect(Unit) {
        storedToken?.let { viewModel.fetchMenuData(branchId, it) }
    }

    val selectedMenuId = remember { mutableStateOf(emptyList<Int>()) }
//    var checkedState = remember { mutableStateOf(false) }
    val checkedStateList = remember { mutableStateListOf<Boolean>() }
//    val showCheckbox = remember { mutableStateOf(false) }
//    val showCheckbox = remember {
//        mutableStateOf(checkedStateList.any { it })
//    }
//    val listIdxMenu = remember { mutableStateOf(emptyList<Int>()) }

    Surface() {
        Column(Modifier.fillMaxSize()) {
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
                                    OrderImageCard3(menuListState[index], navController,
                                        viewModel, selectedMenuId,
                                        checkedStateList = checkedStateList,
                                        position = index
                                    )
                                }
                            }
                        }
                    )
                }
            }


        }
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {

            if (selectedMenuId.value.isNotEmpty()) {
                ExtendedFloatingActionButton(
                    containerColor = PrimarySecond,
                    contentColor = Color.White,
                    onClick = {
//            navController.navigate("order_detail_screen/[5,6]")
                    Log.e("selectedMenuId", selectedMenuId.value.toString())
                    navController.navigate("order_detail_screen/${selectedMenuId.value}")

                },
                    modifier = Modifier.align(Alignment.End).padding(end = 16.dp, bottom = 16.dp)
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "")
                    Text(text = "Tambah Transaksi")
                }
            }
        }
    }
}
/*@Composable
fun FoodScreen(navController: NavHostController, branchId: Int=1) {
    val orderViewModel: OrderViewModel = viewModel()

    val responseState = remember { mutableStateOf<CreateOrderResponse?>(null) }
    val errorState = remember { mutableStateOf<String?>(null) }

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
        Button(
            onClick = {
                *//*val request = CreateOrderRequest(
                    orderNote = "pesen semua",
                    listItem = listOf(
                        MenuItemRequest(menuId = 1, quantity = 3),
                        MenuItemRequest(menuId = 2, quantity = 3)
                    )
                )
                Log.e("FoodScreen", "create request order")
                if (storedToken != null) {
                    orderViewModel.createOrder(request, storedToken)
                    Log.e("FoodScreen", "order process in viewmodel")
                } else {
                    Log.e("FoodScreen", "Token is null")
                }*//*
                navController.navigate("order_detail_screen/[1,2]")

            }
        ) {
            Text("Create Order")
        }
        val response = responseState.value
        val error = errorState.value

        response?.let { response ->
            if (response.success) {
                val responseBody = response.data
                // Handle successful response here
                val branchId = responseBody.branchId
                val userId = responseBody.userId
                val orderNote = responseBody.orderNote
                // ... Update UI based on the response ...
                Log.e("FoodScreen", "Success: $responseBody")
                Log.e("FoodScreen", "Success: ${response.message}")
            } else {
                // Handle unsuccessful response here
                val errorBody = response.message
                Log.e("FoodScreen", "Error: $errorBody")
                // Extract and handle the error message from the errorBody if needed
            }
        }

        error?.let { errorMessage ->
            // Handle error here
        }

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
                                OrderImageCard2(*//*imgRes =R.drawable.food, *//*menuListState[index], navController, viewModel)
                            }
                        }
                    }

                )
            }
        }
    }
}*/

/*
@Composable
fun PressIconButton(
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource =
        remember { MutableInteractionSource() },
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    Button(onClick = onClick, modifier = modifier,
        interactionSource = interactionSource) {
        AnimatedVisibility(visible = isPressed) {
            if (isPressed) {
                Row {
                    icon()
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                }
            }
        }
        text()
    }
}*/
