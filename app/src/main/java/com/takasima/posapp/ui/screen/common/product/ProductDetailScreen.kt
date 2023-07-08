package com.takasima.posapp.ui.screen.common.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.takasima.posapp.R
import com.takasima.posapp.models.MenuViewModel

@Composable
fun ProductDetailScreen(navController: NavHostController) {
    val viewModel= MenuViewModel()
//    val viewModel: MenuViewModel by navController.currentBackStackEntry?.savedStateHandle?.getLiveData<MenuViewModel>("viewModel")?.asFlow()?.first() ?: MenuViewModel()

    val selectedMenu = viewModel.selectedMenuState.value

    Text(text = "detail1")
    if (selectedMenu != null) {
        Text(text = "detail2")
        // Gunakan selectedMenu untuk menampilkan detail produk
        Column(Modifier.fillMaxSize()) {
            Image(painter = painterResource(id = R.drawable.food), contentDescription = "")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = selectedMenu.menu_name)
            Text(text = selectedMenu.menu_price)
            Text(text = selectedMenu.menu_qty)
            Text(text = selectedMenu.menu_type)
            Text(text = selectedMenu.menu_description)
        }
    }
}