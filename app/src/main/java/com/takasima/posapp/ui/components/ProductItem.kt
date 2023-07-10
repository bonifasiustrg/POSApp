package com.takasima.posapp.ui.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.takasima.posapp.R
import com.takasima.posapp.models.MenuViewModel
import com.takasima.posapp.data.product.Menu
import com.takasima.posapp.ui.theme.Primary

@Composable
fun ProductImageCard(imgRes:Int/*
    painter: Painter,
    contentDscription: String,
    title: String,
    modifier: Modifier = Modifier*/
    ) {
    Card(
        modifier = Modifier.height(200.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Box(modifier = Modifier.size(200.dp)) {
            Image(
                painter = painterResource(id = imgRes),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column() {
                    Text(text = "Panna Cotta", style = TextStyle(color = Color.White, fontSize = 16.sp))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Rp50.000", style = TextStyle(color = Color.White, fontSize = 16.sp))
                }
            }
        }
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductImageCard2(/*imgRes:Int=R.drawable.food,*/ menuItem: Menu, navController: NavHostController, viewModel: MenuViewModel) {
    var checkedState by remember { mutableStateOf(false) }
    var showCheckbox by remember { mutableStateOf(false) }
//    val tesIds = arrayListOf(1, 2)
    val tesIds = "[5,6]"
//    val menuIdsList: ArrayList<Int?> = tesIds.map { it }.toCollection(ArrayList())
    val selectedMenu = remember { mutableStateListOf<Menu>() }
    Card(
        modifier = Modifier
            .height(200.dp)
            .combinedClickable(
                onClick = {
                    viewModel.selectedMenuState.value = menuItem
                    Log.e("menuIds", "navController to product detail")

                    navController.navigate("order_detail_screen/$tesIds")
                },
                onLongClick = {
                    viewModel.selectedMenuState.value = menuItem
                    showCheckbox = !showCheckbox
                    checkedState = !checkedState
                    navController.navigate("order_detail_screen")
                }
            )
            /*.clickable {
                viewModel.selectedMenuState.value = menuItem
                navController.navigate("product_detail_screen")
            }*/,
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        var sizeImage by remember { mutableStateOf(IntSize.Zero) }
        val gradient = Brush.verticalGradient(
            colors = listOf(Color.Transparent, Color.Black),
            startY = sizeImage.height.toFloat()/3,  // 1/3
            endY = sizeImage.height.toFloat()
        )
        Box(modifier = Modifier.size(200.dp)) {
            AsyncImage(
                model = menuItem.menu_image,
                contentDescription = menuItem.menu_name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.onGloballyPositioned {
                    sizeImage = it.size
                }
            )

            Box(modifier = Modifier
                .matchParentSize()
                .background(gradient))
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column() {
                    AnimatedVisibility(visible =showCheckbox) {
                        Checkbox(
                            checked = checkedState,
                            onCheckedChange = { isChecked ->
                                checkedState = isChecked
                            }
                        )

                    }
                    Text(text = menuItem.menu_name, style = TextStyle(color = Color.White, fontSize = 16.sp))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Rp${menuItem.menu_price}", style = TextStyle(color = Color.White, fontSize = 16.sp))
                }
            }
        }
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OrderImageCard2(/*imgRes:Int=R.drawable.food,*/ menuItem: Menu, navController: NavHostController, viewModel: MenuViewModel) {
    val tesId= "[5]"
    Card(
        modifier = Modifier
            .height(200.dp)
            .combinedClickable(
                onClick = {
                    viewModel.selectedMenuState.value = menuItem
                    navController.navigate("order_detail_screen/$tesId")
                },
                onLongClick = {
                    viewModel.selectedMenuState.value = menuItem
                    navController.navigate("profile_screen")
                }
            )
        /*.clickable {
            viewModel.selectedMenuState.value = menuItem
            navController.navigate("product_detail_screen")
        }*/,
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        var sizeImage by remember { mutableStateOf(IntSize.Zero) }
        val gradient = Brush.verticalGradient(
            colors = listOf(Color.Transparent, Color.Black),
            startY = sizeImage.height.toFloat()/3,  // 1/3
            endY = sizeImage.height.toFloat()
        )
        Box(modifier = Modifier.size(200.dp)) {
            AsyncImage(
                model = menuItem.menu_image,
                contentDescription = menuItem.menu_name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.onGloballyPositioned {
                    sizeImage = it.size
                }
            )
            Box(modifier = Modifier
                .matchParentSize()
                .background(gradient))
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column() {
                    Button(onClick = { /*TODO*/ },
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(Primary)
                    ) {
//                        Text(text = "HALO")
                        Icon(imageVector = Icons.Default.Add, contentDescription = "")
                    }
                    Text(text = menuItem.menu_name, style = TextStyle(color = Color.White, fontSize = 16.sp))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Rp${menuItem.menu_price}", style = TextStyle(color = Color.White, fontSize = 16.sp))
                }
            }
        }
    }
}

@Composable
fun OrderImageCard(imgRes:Int/*
    painter: Painter,
    contentDscription: String,
    title: String,
    modifier: Modifier = Modifier*/
) {
    Card(
        modifier = Modifier.height(200.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Box(modifier = Modifier.size(200.dp)) {
            Image(
                painter = painterResource(id = imgRes),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column() {
                        Text(text = "Panna Cotta", style = TextStyle(color = Color.White, fontSize = 16.sp))
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Rp50.000", style = TextStyle(color = Color.White, fontSize = 16.sp))
                    }
                    Button(onClick = { /*TODO*/ },
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(Primary)
                    ) {
//                        Text(text = "HALO")
                        Icon(imageVector = Icons.Default.Add, contentDescription = "")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ProductPrev() {
    ProductImageCard(R.drawable.food)
}
@Preview
@Composable
fun ProductOrderPrev() {
    OrderImageCard(R.drawable.drink)
}
