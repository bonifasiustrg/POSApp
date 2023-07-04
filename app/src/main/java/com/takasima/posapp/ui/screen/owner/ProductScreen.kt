package com.takasima.posapp.ui.screen.owner

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.takasima.posapp.ui.components.ProductImageCard

@Composable
fun ProductScreen(navController: NavController) {
//    Text(text = "Product Screen")
    LazyVerticalGrid(
        modifier = Modifier.padding(horizontal = 4.dp),
        columns = GridCells.Fixed(2),
        content =  {
            items(25) {
                Box(modifier = Modifier.padding(8.dp)) {
                    ProductImageCard()
                }
            }
        }

    )
}

@Preview(showBackground = true)
@Composable
fun ProductScreenPrev() {
    ProductScreen(rememberNavController())
}