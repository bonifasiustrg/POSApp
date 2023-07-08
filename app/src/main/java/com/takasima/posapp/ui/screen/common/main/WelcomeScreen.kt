package com.takasima.posapp.ui.screen.common.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.takasima.posapp.R
import com.takasima.posapp.ui.components.HeadingTextComponent3
import com.takasima.posapp.ui.components.NormalTextComponent
import com.takasima.posapp.ui.components.WidthButton
import com.takasima.posapp.ui.components.WidthButtonInverse

@Composable
fun WelcomeScreen(navController:NavHostController) {
//    val navController = rememberNavController()

    Text(text = "Welcome Screen")

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(
            id = R.drawable.welcome), contentDescription = "welcome image",
            modifier = Modifier
                .size(300.dp)
                .fillMaxWidth()

        )
        Spacer(modifier = Modifier.padding(24.dp))
        HeadingTextComponent3(value = "Transaksi Untuk Semua Pembayaran")
        Spacer(modifier = Modifier.padding(8.dp))
        NormalTextComponent(value = "Kini mengakses dan monitoring kasir lebih mudah dalam satu genggaman")

        Spacer(modifier = Modifier.padding(64.dp))
        WidthButton(value = "LOGIN", onClick = {
            navController.navigate("login_screen")
        })
        Spacer(modifier = Modifier.padding(8.dp))
        /*WidthButtonInverse(value = "REGISTER", onClick = {
            navController.navigate("register_screen")
        })*/
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPrev() {
}