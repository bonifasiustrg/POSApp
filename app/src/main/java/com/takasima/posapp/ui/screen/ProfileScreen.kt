package com.takasima.posapp.ui.screen

import DataStoreManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.takasima.posapp.R
import com.takasima.posapp.ui.components.WidthButton
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.runBlocking

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun ProfileScreen(navController: NavHostController) {
    val context = LocalContext.current
    val dataStoreManager = DataStoreManager.getInstance(context)

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(modifier = Modifier
            .clip(CircleShape)
            .size(100.dp),
            alignment = Alignment.TopCenter,
            painter = painterResource(id = R.drawable.profile), contentDescription = "profile picture")
        Spacer(modifier = Modifier.height(8.dp))
        DataElemen("Nama", "BDI's Owner")
        Spacer(modifier = Modifier.height(8.dp))

        DataElemen("Nomor", "6221-777-333")
        Spacer(modifier = Modifier.height(8.dp))

        DataElemen("Email", "bdirestobubat@bdi.com")
        Spacer(modifier = Modifier.height(8.dp))

        DataElemen("Role", "Owner")
        Spacer(modifier = Modifier.heightIn(min = 200.dp, max = 300.dp))


        WidthButton(value = "Edit Profile", onClick = { /*TODO*/ })
        WidthButton(value = "Logout", onClick = {
            // Saat tombol Logout ditekan
            runBlocking {
                dataStoreManager.clearUserToken()
            }
            navController.navigate("welcome_screen")
        })
    }

}

@Composable
fun DataElemen(title:String="Title", content:String="Konten") {
    Column(Modifier.fillMaxWidth()) {
        Text(text = title, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = content)
    }
}

@Preview(showBackground = true)
@Composable
fun prev() {
    ProfileScreen(rememberNavController())
}