package com.takasima.posapp.ui.screen

import DataStoreManager
import android.content.Context
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.loginflow.data.SignInRequest
import com.example.loginflow.data.SignInResponse
import com.takasima.posapp.R
import com.takasima.posapp.Retro
import com.takasima.posapp.UserApi
import com.takasima.posapp.ui.components.HeadingTextComponent3
import com.takasima.posapp.ui.components.MyTextFieldComponent
import com.takasima.posapp.ui.components.NormalTextComponent
import com.takasima.posapp.ui.components.PasswordTextFieldComponent
import com.takasima.posapp.ui.components.WidthButton
import com.takasima.posapp.ui.theme.Primary
import com.takasima.posapp.ui.theme.Typography
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun LoginScreen(navController: NavHostController) {
//    val navController = rememberNavController()
    val context = LocalContext.current

    Text(text = "Login Screen")
    var email = remember {
        mutableStateOf("")
    }
    var password = remember {
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

            HeadingTextComponent3(value = "Username")
            MyTextFieldComponent(labelValue = "Masukkan id/email anda...", Icons.Default.Email, email)
            Spacer(modifier = Modifier.height(8.dp))
            HeadingTextComponent3(value = "Password")
            PasswordTextFieldComponent(labelValue = "Masukkan password anda...", Icons.Default.Lock, password)

            Spacer(modifier = Modifier.height(48.dp))
            /*WidthButton(value = "LOGIN", onClick = {
                navController.navigate("posapp")
            })*/

            LoginButtonComponent(email, password, navController, context)
//            Spacer(modifier = Modifier.height(20.dp))
//
//            RegisterButtonComponent(navController)
        }
    }
}


@Composable
fun LoginButtonComponent(email: MutableState<String>, password: MutableState<String>, navController: NavController, context: Context) {
    val dataStoreManager = remember { DataStoreManager.getInstance(context) }

    Button(colors = ButtonDefaults.buttonColors(containerColor = Primary),
        modifier = Modifier.fillMaxWidth()
        ,onClick = {
        login(email.value, password.value, navController, dataStoreManager = dataStoreManager)
        password.value = ""
        email.value = ""
    }) {
        Text(text = "LOGIN")
    }
}

@OptIn(DelicateCoroutinesApi::class)
fun login(email: String, password: String, navController: NavController, dataStoreManager: DataStoreManager) {
    val request = SignInRequest()
    request.email = email.trim()
    request.password = password.trim()

    val retro = Retro().getRetroClientInstance().create(UserApi::class.java)
    val storedToken = runBlocking { dataStoreManager.getAuthToken.first() }

    retro.login(request).enqueue(object : Callback<SignInResponse> {
        override fun onResponse(
            call: Call<SignInResponse>,
            response: Response<SignInResponse>
        ) {
            if (response.isSuccessful) {
                val user = response.body()
                if (user != null && user.success == true) {

                    Log.e("status", "Login Successfully")

                    Log.e("token", user.data?.token ?: "Token not available")
                    val token = user.data?.token ?: ""
                    GlobalScope.launch {
                        dataStoreManager.setUserToken(token)
                    }

                    Log.e("email", user.data?.user?.email ?: "Email not available")

                    navController.navigate(route = "posapp")
                } else {
                    Log.e(
                        "error",
                        "User response body is null, maybe email and password are wrong"
                    )
                }
            } else {
                // Handle error response
                val errorBody = response.errorBody()?.string()
                Log.e("error", "Error response: $errorBody")
                // Handle the error UI or show a toast message
                // You can also extract the error message from the errorBody and display it to the user
            }
        }

        override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
            Log.e("error", t.message!! ?: "Unknown error occurred")
        }

    })

}

@Preview
@Composable
fun LoginPrev() {
}