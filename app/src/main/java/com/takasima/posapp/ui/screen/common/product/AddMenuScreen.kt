package com.takasima.posapp.ui.screen.common.product

import DataStoreManager
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.takasima.posapp.Retro
import com.takasima.posapp.UserApi
import com.takasima.posapp.data.addmenu.CreateProductRequest
import com.takasima.posapp.data.addmenu.CreateProductResponse
import com.takasima.posapp.ui.components.HeadingTextComponent3
import com.takasima.posapp.ui.components.MyTextFieldComponent
import com.takasima.posapp.ui.theme.Primary
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun AddMenuScreen(navController: NavHostController) {
    val context = LocalContext.current

    var branch_id = remember {
        mutableStateOf("")
    }
    var menu_type = remember {
        mutableStateOf("")
    }
    var menu_name = remember {
        mutableStateOf("")
    }
    var menu_price = remember {
        mutableStateOf("")
    }
    var menu_description = remember {
        mutableStateOf("")
    }
    var menu_qty = remember {
        mutableStateOf("")
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(8.dp)
    ) {
        Column(verticalArrangement = Arrangement.Center) {

            HeadingTextComponent3(value = "BranchId")
            MyTextFieldComponent(labelValue = "Masukkan BranchId", Icons.Default.Edit, branch_id)
            Spacer(modifier = Modifier.height(8.dp))

            HeadingTextComponent3(value = "Manu Type")
            MyTextFieldComponent(labelValue = "Masukkan Tipe menu...", Icons.Default.Edit, menu_type)
            Spacer(modifier = Modifier.height(8.dp))

            HeadingTextComponent3(value = "Menu Name")
            MyTextFieldComponent(labelValue = "Masukkan nama menu...", Icons.Default.Edit, menu_name)
            Spacer(modifier = Modifier.height(8.dp))

            HeadingTextComponent3(value = "Menu Price")
            MyTextFieldComponent(labelValue = "Masukkan harga menu...", Icons.Default.Edit, menu_price)
            Spacer(modifier = Modifier.height(8.dp))

            HeadingTextComponent3(value = "Menu Description")
            MyTextFieldComponent(labelValue = "Deskripsi menu...", Icons.Default.Edit, menu_description)
            Spacer(modifier = Modifier.height(8.dp))

            HeadingTextComponent3(value = "Menu Quantity")
            MyTextFieldComponent(labelValue = "Masukkan jumlah item ...", Icons.Default.Edit, menu_qty)
            Spacer(modifier = Modifier.height(8.dp))

//            Spacer(modifier = Modifier.height(48.dp))

            AddMenuButton(branch_id, menu_type, menu_name, menu_price, menu_description, menu_qty, navController = navController, context)
        }
    }
}
@Composable
fun AddMenuButton(
    branch_id: MutableState<String>,
    menu_type: MutableState<String>,
    menu_name: MutableState<String>,
    menu_price: MutableState<String>,
    menu_description: MutableState<String>,
    menu_qty: MutableState<String>,
    navController: NavHostController,
    context: Context
) {
    val dataStoreManager = remember { DataStoreManager.getInstance(context) }

    Button(colors = ButtonDefaults.buttonColors(containerColor = Primary),
        modifier = Modifier.fillMaxWidth()
        ,onClick = {
            addMenu(branch_id, menu_type, menu_name, menu_price, menu_description, menu_qty, navController, dataStoreManager)
            branch_id.value = ""
            menu_type.value = ""
            menu_name.value = ""
            menu_price.value = ""
            menu_description.value = ""
            menu_qty.value = ""
        }) {
        Text(text = "ADD MENU")
    }
}
fun addMenu(
    branch_id: MutableState<String>,
    menu_type: MutableState<String>,
    menu_name: MutableState<String>,
    menu_price: MutableState<String>,
    menu_description: MutableState<String>,
    menu_qty: MutableState<String>,
    navController: NavHostController,
    dataStoreManager: DataStoreManager
) {
//    val retrofit = Retro().getRetroClientInstance()
//    val api = retrofit.create(UserApi::class.java)

    val request = CreateProductRequest()
    val token = runBlocking { dataStoreManager.getAuthToken.first() }

    request.branchId = "123"
    request.menuType = "Food"
    request.menuName = "Nasi Goreng"
    request.menuPrice = "10.99"
    request.menuDescription = "Nasi goreng spesial"
    request.menuQty = "5"

    val retro = Retro().getRetroClientInstance().create(UserApi::class.java)
    val header = "Bearer $token"
//    val call = api.createProduct(createProductRequest)
    retro.createProduct(request, header).enqueue(object : Callback<CreateProductResponse> {
        override fun onResponse(
            call: Call<CreateProductResponse>,
            response: Response<CreateProductResponse>
        ) {
            if (response.isSuccessful) {
//                val user = response.body()
                val productResponse = response.body()
                if (productResponse != null && productResponse.success == true) {

                    Log.e("status", productResponse.message.toString())

                    val data = productResponse.data
                    // Akses properti data sesuai kebutuhan Anda
                    val branchId = data?.branch_id
                    val userId = data?.user_id
                    val menuName = data?.menu_name
                    val menuQty = data?.menu_qty
                    val menuType = data?.menu_type
                    val menuPrice = data?.menu_price
                    val menuDescription = data?.menu_description
                    val menuImage = data?.menu_image
                    val menuStatus = data?.menu_status
                    Log.e("menu", productResponse.data?.toString() ?: "Menu not available")
                    Log.e("menu", branchId ?: "Menu not available")
                    Log.e("menu", userId ?: "Menu not available")
                    Log.e("menu", menuName ?: "Menu not available")
                    Log.e("menu", menuQty ?: "Menu not available")
                    Log.e("menu", menuType ?: "Menu not available")
                    Log.e("menu", menuPrice ?: "Menu not available")
                    Log.e("menu", menuDescription ?: "Menu not available")
                    Log.e("menu", menuImage ?: "Menu not available")
                    Log.e("menu", menuStatus ?: "Menu not available")
                    navController.navigate(route = "BottomMenu.Orders.route")
                } else {
                    Log.e(
                        "error1",
                        "User response body is null, maybe email and password are wrong"
                    )
                }
            } else {
                // Handle error response
                val errorBody = response.errorBody()?.string()
                Log.e("error2", "Error response: $errorBody")
                // Handle the error UI or show a toast message
                // You can also extract the error message from the errorBody and display it to the user
            }

        }

        override fun onFailure(call: Call<CreateProductResponse>, t: Throwable) {
            Log.e("error", t.message!! ?: "Unknown error occurred")

        }
    })
}


@Preview
@Composable
fun AddMenuScreenPrev() {
    AddMenuScreen(navController = rememberNavController())
}