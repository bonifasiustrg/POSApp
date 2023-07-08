package com.takasima.posapp.ui.screen.owner.branch

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.takasima.posapp.network.Retro
import com.takasima.posapp.network.UserApi
import com.takasima.posapp.data.branch.CreateBranchRequest
import com.takasima.posapp.data.branch.CreateBranchResponse
import com.takasima.posapp.ui.components.BottomMenu
import com.takasima.posapp.ui.components.HeadingTextComponent3
import com.takasima.posapp.ui.components.MyTextFieldComponent
import com.takasima.posapp.ui.theme.Primary
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun AddBranchScreen(navController: NavHostController) {
    val context = LocalContext.current

//    var branch_id = remember {
//        mutableStateOf("")
//    }
    var branch_name = remember {
        mutableStateOf("")
    }
    var branch_address = remember {
        mutableStateOf("")
    }
    var branch_phone = remember {
        mutableStateOf("")
    }
    var branch_email = remember {
        mutableStateOf("")
    }
    var branch_status = remember {
        mutableStateOf("")
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(8.dp)
    ) {
        Column(verticalArrangement = Arrangement.Center) {

            HeadingTextComponent3(value = "Branch Name")
            MyTextFieldComponent(labelValue = "Masukkan Branch Name", Icons.Default.Edit, branch_name)
            Spacer(modifier = Modifier.height(8.dp))

            HeadingTextComponent3(value = "branch_address")
            MyTextFieldComponent(labelValue = "Masukkan Tipe menu...", Icons.Default.Edit, branch_address)
            Spacer(modifier = Modifier.height(8.dp))

            HeadingTextComponent3(value = "branch_phone")
            MyTextFieldComponent(labelValue = "Masukkan nama menu...", Icons.Default.Edit, branch_phone)
            Spacer(modifier = Modifier.height(8.dp))

            HeadingTextComponent3(value = "branch_email")
            MyTextFieldComponent(labelValue = "Masukkan harga menu...", Icons.Default.Edit, branch_email)
            Spacer(modifier = Modifier.height(8.dp))

//            HeadingTextComponent3(value = "Menu Description")
//            MyTextFieldComponent(labelValue = "Deskripsi menu...", Icons.Default.Edit, branch_status)
//            Spacer(modifier = Modifier.height(8.dp))


//            Spacer(modifier = Modifier.height(48.dp))

            AddMenuButton(branch_name, branch_address, branch_phone, branch_email, branch_status, navController = navController, context)
        }
    }
}
@Composable
fun AddMenuButton(
//    branch_id: MutableState<String>,
    branch_name: MutableState<String>,
    branch_address: MutableState<String>,
    branch_phone: MutableState<String>,
    branch_email: MutableState<String>,
    branch_status: MutableState<String>,
    navController: NavHostController,
    context: Context,
) {
    val dataStoreManager = remember { DataStoreManager.getInstance(context) }

    Button(colors = ButtonDefaults.buttonColors(containerColor = Primary),
        modifier = Modifier.fillMaxWidth()
        ,onClick = {
            addBranch(branch_name, branch_address, branch_phone, branch_email, branch_status, navController, dataStoreManager)
            branch_name.value = ""
            branch_address.value = ""
            branch_phone.value = ""
            branch_email.value = ""
        }) {
        Text(text = "ADD MENU")
    }
}

fun addBranch(
    branch_name: MutableState<String>,
    branch_address: MutableState<String>,
    branch_phone: MutableState<String>,
    branch_email: MutableState<String>,
    branch_status: MutableState<String>,
    navController: NavHostController,
    dataStoreManager: DataStoreManager
) {
//    val retrofit = Retro().getRetroClientInstance()
//    val api = retrofit.create(UserApi::class.java)

    val request = CreateBranchRequest()
    val token = runBlocking { dataStoreManager.getAuthToken.first() }

//    request.branch_name = "BDI Resto Bandung"
//    request.branch_address = "depan SD"
//    request.branch_phone = "0808080880"
//    request.branch_email = "branch5@pos.com"
    request.branch_name = branch_name.value
    request.branch_address = branch_address.value
    request.branch_phone = branch_phone.value
    request.branch_email = branch_email.value

    val retro = Retro.getRetroClientInstance().create(UserApi::class.java)
    val header = "Bearer $token"
//    val call = api.createProduct(createProductRequest)
    retro.createBranch(request, header).enqueue(object : Callback<CreateBranchResponse> {
        override fun onResponse(
            call: Call<CreateBranchResponse>,
            response: Response<CreateBranchResponse>
        ) {
            if (response.isSuccessful) {
//                val user = response.body()
                val productResponse = response.body()
                if (productResponse != null && productResponse.success == true) {

                    Log.e("status", productResponse.message.toString())

                    val data = productResponse.data
                    // Akses properti data sesuai kebutuhan Anda
                    val branchName = data?.branch_name
                    val branchAddress = data?.branch_address
                    val branchPhone = data?.branch_phone
                    val branchEmail = data?.branch_email
                    Log.e("branch", branchName ?: "No data")
                    Log.e("branch", branchAddress ?: "No data")
                    Log.e("branch", branchPhone ?: "No data")
                    Log.e("branch", branchEmail ?: "No data")

                    navController.navigate(route = BottomMenu.Shops.route)
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

        override fun onFailure(call: Call<CreateBranchResponse>, t: Throwable) {
            Log.e("error", t.message!! ?: "Unknown error occurred")

        }
    })
}
