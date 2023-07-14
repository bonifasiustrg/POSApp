package com.takasima.posapp.ui.screen.common.main

import DataStoreManager
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.takasima.posapp.data.login.SignInRequest
import com.takasima.posapp.data.login.SignInResponse
import com.takasima.posapp.R
import com.takasima.posapp.network.Retro
import com.takasima.posapp.network.UserApi
import com.takasima.posapp.ui.components.HeadingTextComponent3
import com.takasima.posapp.ui.components.MyTextFieldComponent
import com.takasima.posapp.ui.components.NormalTextComponent
import com.takasima.posapp.ui.components.PasswordTextFieldComponent
import com.takasima.posapp.ui.theme.Primary
import com.takasima.posapp.ui.theme.Typography
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun LoginScreen(navController: NavHostController) {
//    val navController = rememberNavController()
    val context = LocalContext.current

    Text(text = "Login Screen")
    val email = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Image(painter = painterResource(
                id = R.drawable.money_tree), contentDescription = "welcome image",
                modifier = Modifier
                    .size(300.dp)
                    .fillMaxWidth()
            )
            Text(text = "Login Akun", style = Typography.headlineSmall, fontWeight = FontWeight.Bold)
            NormalTextComponent(value = "Masuk ke dalam akun untuk pantau transaksimu")
            Spacer(Modifier.height(20.dp))

            HeadingTextComponent3(value = "Email")
            MyTextFieldComponent(labelValue = "Masukkan email", Icons.Default.Email, email)
            Spacer(modifier = Modifier.height(8.dp))
            HeadingTextComponent3(value = "Password")
            PasswordTextFieldComponent(labelValue = "Masukkan password", Icons.Default.Lock, password)

            Spacer(modifier = Modifier.height(48.dp))

            LoginButtonComponent(email, password, navController, context)
        }
    }
}


@Composable
fun ColumnScope.LoginButtonComponent(email: MutableState<String>, password: MutableState<String>, navController: NavController, context: Context) {
    val dataStoreManager = remember { DataStoreManager.getInstance(context) }
    val clicked = remember {
        mutableStateOf(false)
    }
    val loginStatus = remember {
        mutableStateOf(false)
    }
    Button(colors = ButtonDefaults.buttonColors(containerColor = Primary),
        modifier = Modifier.fillMaxWidth()
        ,onClick = {
            if (email.value.isNotEmpty() || password.value.isNotEmpty()) {
                clicked.value = true
                login(email.value, password.value, navController, dataStoreManager = dataStoreManager, loginStatus = loginStatus)
                Toast.makeText(context, "Login Successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Try Again, email and password are empty", Toast.LENGTH_SHORT).show()
            }
            password.value = ""
            email.value = ""
        }) {
        Text(text = "LOGIN")
    }
    if (clicked.value) {
        Log.e("clicked", "clicked")
        CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}

@OptIn(DelicateCoroutinesApi::class)
fun login(email: String, password: String, navController: NavController, dataStoreManager: DataStoreManager, loginStatus: MutableState<Boolean>) {
    val request = SignInRequest()
    request.email = email.trim()
    request.password = password.trim()

    val retro = Retro.getRetroClientInstance().create(UserApi::class.java)
//    val storedToken = runBlocking { dataStoreManager.getAuthToken.first() }

    retro.login(request).enqueue(object : Callback<SignInResponse> {
        override fun onResponse(
            call: Call<SignInResponse>,
            response: Response<SignInResponse>
        ) {
            if (response.isSuccessful) {
                val user = response.body()
                if (user != null && user.success == true) {
                    loginStatus.value = true

                    Log.e("status", "Login Successfully")
                    Log.e("token", user.data?.token ?: "Token not available")
                    Log.e("role", user.data?.role ?: "Role not available")
                    val token = user.data?.token ?: ""
                    val role = user.data?.role ?: ""
                    GlobalScope.launch {
                        dataStoreManager.setUserToken(token)
                        dataStoreManager.setUserRole(role)
                    }

                    Log.e("email", user.data?.user?.email ?: "Email not available")

                    navController.navigate(route = "posapp")
                } else {
                    Log.e(

                        "error",
                        "User response body is null, maybe email and password are wrong"
                    )
//                    loginStatus.value = false

                }
            } else {
                // Handle error response
                val errorBody = response.errorBody()?.string()
                Log.e("error", "Error response: $errorBody")
//                loginStatus.value = false

                // Handle the error UI or show a toast message
                // You can also extract the error message from the errorBody and display it to the user
            }
        }

        override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
            Log.e("error", t.message!! ?: "Unknown error occurred")
//            loginStatus.value = false

        }

    })

}

@Preview
@Composable
fun LoginPrev() {
}