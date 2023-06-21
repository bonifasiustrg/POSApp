package com.takasima.posapp.ui.screen

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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.takasima.posapp.R
import com.takasima.posapp.ui.components.NormalTextComponent
import com.takasima.posapp.ui.components.TextFieldCommon
import com.takasima.posapp.ui.components.WidthButton
import com.takasima.posapp.ui.theme.Typography

@Composable
fun RegisterScreen(navController: NavController) {
    Text(text = "Register Screen")
    val idPegawai = remember {
        mutableStateOf("")
    }
    val namaLengkap = remember {
        mutableStateOf("")
    }
    val noHP = remember {
        mutableStateOf("")
    }
    val email = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val passwordConfirm = remember {
        mutableStateOf("")
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column() {
            Text(text = "Daftar Akun", style = Typography.headlineSmall, fontWeight = FontWeight.Bold)
            NormalTextComponent(value = "Daftar akun untuk memulai transaksi pertamamu")

            TextFieldCommon("ID Pegawai", "Masukkan id pegawai anda...", idPegawai)
            TextFieldCommon("Nama Lengkap", "Masukkan nama lengkap anda...", namaLengkap)
            TextFieldCommon("No Handphone", "Masukkan no handphone anda...", noHP)
            TextFieldCommon("E-mail", "Masukkan e-mail anda...", email)
            TextFieldCommon("Password", "Masukkan password anda...", password)
            TextFieldCommon("Konfirmasi Password", "Konfirmasi password anda...", passwordConfirm)

            Spacer(modifier = Modifier.height(36.dp))
            WidthButton(value = "REGISTER", onClick = {
                navController.navigate("login_screen")
            })
        }
    }
}

@Preview
@Composable
fun RegisterPrev() {
    RegisterScreen(navController = rememberNavController())
}