package com.takasima.posapp.ui.screen.common

import DataStoreManager
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.takasima.posapp.R
import com.takasima.posapp.Retro
import com.takasima.posapp.UserApi
import com.takasima.posapp.data.addmenu.CreateProductResponse
import com.takasima.posapp.ui.components.OrderImageCard
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun FoodScreen(navController: NavHostController) {
    Text(text = "Food Screen")
    LazyVerticalGrid(modifier = Modifier.padding(horizontal = 4.dp),
        columns = GridCells.Fixed(2),
        content =  {
            items(25) {
                Box(modifier = Modifier.padding(8.dp)) {
                    OrderImageCard(R.drawable.food)
                }
            }
        }

    )
}
@Composable
fun ProductFoodScreen(navController: NavHostController) {
//    val productList = remember { mutableStateListOf<CreateProductResponse.Data>() }
    val productList: MutableState<List<CreateProductResponse.Data>> = remember { mutableStateOf(emptyList()) }

    val context = LocalContext.current

    /*LazyVerticalGrid(modifier = Modifier.padding(horizontal = 4.dp),
        columns = GridCells.Fixed(2),
        content =  {
            items(25) {
                Box(modifier = Modifier.padding(8.dp)) {
                    ProductImageCard(R.drawable.food)
                }
            }
        }

    )*/
    val dataStoreManager = remember { DataStoreManager.getInstance(context) }
//    val token = runBlocking { dataStoreManager.getAuthToken.first() }
    val token by dataStoreManager.getAuthToken.collectAsState(initial = "")

    /*LaunchedEffect(Unit) {
        fetchProducts(token, productList)
    }*/
//    Text(text = fetchProducts(token, productList).toString())
    Text(text = "Product Food Screen")
    /*Surface(modifier = Modifier.fillMaxSize()) {
        Text(text = "Food Screen")

        LazyColumn {
            items(productList) { product ->
                ProductItem(product)
            }
        }
    }*/
}

fun fetchProducts(token: String?,  productList: MutableState<List<CreateProductResponse.Data>>) {
    val retrofit = Retro().getRetroClientInstance()
    val api = retrofit.create(UserApi::class.java)

    val call =
        token?.let { api.getProducts(it) } // Ganti dengan endpoint yang sesuai untuk mengambil data produk
    call?.enqueue(object : Callback<List<CreateProductResponse.Data>> {
        override fun onResponse(
            call: Call<List<CreateProductResponse.Data>>,
            response: Response<List<CreateProductResponse.Data>>
        ) {
            if (response.isSuccessful) {
                val products = response.body()
                if (products != null) {
                    productList.value = products.toMutableList() // Simpan daftar produk ke dalam productList
                    Log.e("product", productList.value.toString())
                }
            } else {
                // Handle error response
                val errorBody = response.errorBody()?.string()
                Log.e("error1", "Error response: $errorBody")
                // Handle the error UI or show a toast message
            }
        }

        override fun onFailure(call: Call<List<CreateProductResponse.Data>>, t: Throwable) {
            // Handle network or request error
            Log.e("error2", t.message!! ?: "Unknown error occurred")

        }
    })
}