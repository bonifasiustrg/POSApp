package com.takasima.posapp.ui.screen.common.product

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.takasima.posapp.ui.components.WidthButton

@Composable
fun ProductOption(navController: NavHostController) {
    Column() {
//        val context = LocalContext.current
//        val dataStoreManager = DataStoreManager.getInstance(context)

        WidthButton(value = "Tambah Produk", onClick = {
            navController.navigate("product_add_screen")
        })

        WidthButton(value = "Edit Produk", onClick = {
//            navController.navigate("welcome_screen")
        })

        WidthButton(value = "Hapus Produk", onClick = {
//            navController.navigate("welcome_screen")
        })
    }
}