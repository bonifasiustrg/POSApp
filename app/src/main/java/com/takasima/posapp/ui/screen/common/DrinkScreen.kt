package com.takasima.posapp.ui.screen.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.takasima.posapp.R
import com.takasima.posapp.ui.components.OrderImageCard
import com.takasima.posapp.ui.components.ProductImageCard

@Composable
fun DrinkScreen(navController: NavHostController) {
    Text(text = "Drink Screen")
    LazyVerticalGrid(modifier = Modifier.padding(horizontal = 4.dp),
        columns = GridCells.Fixed(2),
        content =  {
            items(25) {
                Box(modifier = Modifier.padding(8.dp)) {
                    OrderImageCard(R.drawable.drink)
                }
            }
        }

    )
}
@Composable
fun ProductDrinkScreen(navController: NavHostController) {
    Text(text = "Drink Screen")
    LazyVerticalGrid(modifier = Modifier.padding(horizontal = 4.dp),
        columns = GridCells.Fixed(2),
        content =  {
            items(25) {
                Box(modifier = Modifier.padding(8.dp)) {
                    ProductImageCard(imgRes = R.drawable.drink)
                }
            }
        }

    )
}