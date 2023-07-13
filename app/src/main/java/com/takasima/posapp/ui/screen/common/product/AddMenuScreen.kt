package com.takasima.posapp.ui.screen.common.product

import DataStoreManager
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.takasima.posapp.network.Retro
import com.takasima.posapp.network.UserApi
import com.takasima.posapp.models.MenuViewModel
import com.takasima.posapp.data.product.CreateProductRequest
import com.takasima.posapp.data.product.CreateProductResponse
import com.takasima.posapp.ui.components.BottomMenu
import com.takasima.posapp.ui.components.HeadingTextComponent3
import com.takasima.posapp.ui.components.MyTextFieldComponent
import com.takasima.posapp.ui.components.TextFieldCommon
import com.takasima.posapp.ui.theme.Primary
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMenuScreen(navController: NavHostController) {
    val context = LocalContext.current

    val branch_id = remember {
        mutableStateOf("")
    }
    val menu_type = remember {
        mutableStateOf("daily_stock")
    }
    val menu_name = remember {
        mutableStateOf("")
    }
    val menu_price = remember {
        mutableStateOf("")
    }
    val menu_description = remember {
        mutableStateOf("")
    }
    val menu_qty = remember {
        mutableStateOf("")
    }
    val coffeeDrinks = arrayOf("Daily Stock", "Unit Stock")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(coffeeDrinks[0]) }
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp),verticalArrangement = Arrangement.Top) {

            HeadingTextComponent3(value = "Menu Type")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {
                    TextField(
                        value = selectedText,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier.menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        coffeeDrinks.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item) },
                                onClick = {
                                    selectedText = item
                                    menu_type.value = when (item) {
                                        "Daily Stock" -> "daily_stock"
                                        "Unit Stock" -> "unit_stock"
                                        else -> "undefined_stock"
                                    }
                                    expanded = false
                                    Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                                }
                            )
                        }
                    }
                }
            }
//            MyTextFieldComponent(labelValue = "Masukkan Tipe menu...", Icons.Default.Edit, menu_type)
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

            //upload image
            val base64Text = remember { mutableStateOf("") }
            RequestContentPermission(base64Text)

            AddMenuButton(branch_id, menu_type, menu_name, menu_price, menu_description, menu_qty, base64Text, navController = navController, context)
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
    menu_image: MutableState<String>? = null,
    navController: NavHostController,
    context: Context,
) {
    val dataStoreManager = remember { DataStoreManager.getInstance(context) }
    val viewModel: MenuViewModel = viewModel() // Mengambil instance dari MenuViewModel

    Button(colors = ButtonDefaults.buttonColors(containerColor = Primary),
        modifier = Modifier.fillMaxWidth()
        ,onClick = {
            addMenu(branch_id, menu_type, menu_name, menu_price, menu_description, menu_qty, menu_image, navController, dataStoreManager)
            Toast.makeText(context, "Menu berhasil ditambahkan", Toast.LENGTH_SHORT).show()
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
    menu_image: MutableState<String>?,
    navController: NavHostController,
    dataStoreManager: DataStoreManager
) {
//    val retrofit = Retro().getRetroClientInstance()
//    val api = retrofit.create(UserApi::class.java)

    val request = CreateProductRequest()
    val token = runBlocking { dataStoreManager.getAuthToken.first() }

//    request.branchId = branch_id.value
    request.menuType = menu_type.value
    request.menuName = menu_name.value
    request.menuPrice = menu_price.value
    request.menuDescription = menu_description.value
    request.menuQty = menu_qty.value
    request.menuImage = menu_image?.value

    val retro = Retro.getRetroClientInstance().create(UserApi::class.java)
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


                    navController.navigate(route = BottomMenu.Products.route) {
//                        popUpTo("history_screen") {
//                            inclusive = true
//
//                        }
                        navController.popBackStack()
                    }
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