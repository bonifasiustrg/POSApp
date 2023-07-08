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


@Composable
fun FoodScreen(navController: NavHostController) {
    Text(text = "Food Screen")
    LazyVerticalGrid(modifier = Modifier.padding(horizontal = 4.dp),
        columns = GridCells.Fixed(2),
        content =  {
            items(25) {
                Box(modifier = Modifier.padding(8.dp)) {
                    OrderImageCard(R.drawable.food)
                }
            }
        }

    )
}

/*suspend fun getMenuList(): Result<List<Menu>> {
    return withContext(Dispatchers.IO) {
        try {
            val retrofit = Retro.getRetroClientInstance()
            val api = retrofit.create(UserApi::class.java)

            val response = api.getMenuList()
            if (response.isSuccessful) {
                val menuResponse = response.body()
                if (menuResponse != null && menuResponse.success == true) {
                    val menuList = menuResponse.data
                    Result.Success(menuList)
                } else {
                    Result.Error("Failed to get menu list.")
                }
            } else {
                Result.Error("Error: ${response.code()}")
            }
        } catch (e: Exception) {
            Result.Error("Network request failed.")
        }
    }
}*/
