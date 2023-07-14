package com.takasima.posapp.ui.screen.owner.branch

import android.content.Context
import android.util.Log
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.takasima.posapp.data.branch.CreateBranchRequest
import com.takasima.posapp.data.branch.CreateBranchResponse
import com.takasima.posapp.data.branch.CreateStaffRequest
import com.takasima.posapp.data.branch.CreateStaffResponse
import com.takasima.posapp.network.Retro
import com.takasima.posapp.network.UserApi
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
fun AddStaffScreen(navController: NavHostController, branchId:Int) {
    val ctx = LocalContext.current
    val dataStoreManager = DataStoreManager.getInstance(ctx)
    val storedToken = runBlocking { dataStoreManager.getAuthToken.first() }

    val name = remember {mutableStateOf("")}
    val email = remember {mutableStateOf("")}
    val password = remember {mutableStateOf("")}
    val no_hp = remember {mutableStateOf("")}
    val address = remember {mutableStateOf("")}


    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Form Pendaftaran Staf", modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 24.sp, fontWeight = FontWeight.Bold
        )

        HeadingTextComponent3(value = "Branch Id")
        Text(text = branchId.toString())
        Spacer(modifier = Modifier.height(8.dp))

        HeadingTextComponent3(value = "Nama Lengkap")
        MyTextFieldComponent(labelValue = "Masukkan Nama Staff", Icons.Default.Edit, name)
        Spacer(modifier = Modifier.height(8.dp))

        HeadingTextComponent3(value = "Email")
        MyTextFieldComponent(labelValue = "Masukkan Email", Icons.Default.Edit, email)
        Spacer(modifier = Modifier.height(8.dp))

        HeadingTextComponent3(value = "Password")
        MyTextFieldComponent(labelValue = "Buat Password", Icons.Default.Edit, password)
        Spacer(modifier = Modifier.height(8.dp))

        HeadingTextComponent3(value = "No HP")
        MyTextFieldComponent(labelValue = "Masukkan No HP", Icons.Default.Edit, no_hp)
        Spacer(modifier = Modifier.height(8.dp))

        HeadingTextComponent3(value = "Alamat Staff")
        MyTextFieldComponent(labelValue = "Masukkan alamat", Icons.Default.Edit, address)
        Spacer(modifier = Modifier.height(8.dp))

        AddStaffButton(branchId, name, email, password, no_hp, address, navController, ctx, storedToken!!)
    }
}

@Composable
fun AddStaffButton(
    branchId: Int,
    name: MutableState<String>,
    email: MutableState<String>,
    password: MutableState<String>,
    noHP: MutableState<String>,
    address: MutableState<String>,
    navController: NavHostController,
    context: Context,
    token: String
) {
    val dataStoreManager = remember { DataStoreManager.getInstance(context) }

    Button(colors = ButtonDefaults.buttonColors(containerColor = Primary),
        modifier = Modifier.fillMaxWidth()
        ,onClick = {
            addStaff(branchId, name, email, password, noHP, address, navController, context, token)
            name.value = ""
            email.value = ""
            password.value = ""
            noHP.value = ""
            address.value = ""
        }) {
        Text(text = "ADD MENU")
    }
}

fun addStaff(
    branchId: Int,
    name: MutableState<String>,
    email: MutableState<String>,
    password: MutableState<String>,
    noHP: MutableState<String>,
    address: MutableState<String>,
    navController: NavHostController,
    context: Context,
    token: String
) {
    val request = CreateStaffRequest()
    request.branch = branchId
    request.name = name.value
    request.email = email.value
    request.password = password.value
    request.no_hp = noHP.value
    request.address = address.value


    val retro = Retro.getRetroClientInstance().create(UserApi::class.java)
    val header = "Bearer $token"
    retro.createStaff(request, header).enqueue(object : Callback<CreateStaffResponse> {
        override fun onResponse(
            call: Call<CreateStaffResponse>,
            response: Response<CreateStaffResponse>
        ) {
            if (response.isSuccessful) {
//                val user = response.body()
                val productResponse = response.body()
                if (productResponse != null && productResponse.success) {

                    Log.e("status", productResponse.message.toString())

                    val data = productResponse.data
                    Log.e("branch", data.toString() ?: "No data")

                    navController.navigate(route = "branch_staff_screen/$branchId") {
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

        override fun onFailure(call: Call<CreateStaffResponse>, t: Throwable) {
            Log.e("error", t.message!! ?: "Unknown error occurred")

        }
    })
}