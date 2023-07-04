package com.takasima.posapp.ui.screen

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.takasima.posapp.R
import com.takasima.posapp.ui.components.HeadingTextComponent3
import com.takasima.posapp.ui.components.MyTextFieldComponent
import com.takasima.posapp.ui.components.NormalTextComponent
import com.takasima.posapp.ui.components.PasswordTextFieldComponent
import com.takasima.posapp.ui.components.WidthButton
import com.takasima.posapp.ui.theme.Typography

@Composable
fun LoginScreen(navController: NavHostController) {
//    val navController = rememberNavController()

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

            HeadingTextComponent3(value = "Username")
            MyTextFieldComponent(labelValue = "Masukkan id/email anda...", Icons.Default.Email, email)
            Spacer(modifier = Modifier.height(8.dp))
            HeadingTextComponent3(value = "Password")
            PasswordTextFieldComponent(labelValue = "Masukkan password anda...", Icons.Default.Lock, password)

            Spacer(modifier = Modifier.height(48.dp))
            WidthButton(value = "LOGIN", onClick = {
                navController.navigate("posapp")
            })

//            LoginButtonComponent(email, password, navController)
//            Spacer(modifier = Modifier.height(20.dp))
//
//            RegisterButtonComponent(navController)
        }
    }
}


@Composable
fun LoginButtonComponent(email: MutableState<String>, password: MutableState<String>, navController: NavController) {
    Button(onClick = {
//        login(email.value, password.value, navController)
        password.value = ""
        email.value = ""
    }) {
        Text(text = "LOGIN")
    }
}

/*@Composable
fun RegisterButtonComponent(navController: NavController) {
    Button(colors = ButtonDefaults.buttonColors(Color.White), border = BorderStroke(1.dp, Color.Gray),
        onClick = {
        navController.navigate("posapp") {
            popUpTo(navController.graph.startDestinationId)
            launchSingleTop = true
        }
    }) {
        Text(text = "REGISTER", color = Color.Black)
    }
}*/

@Preview
@Composable
fun LoginPrev() {
}