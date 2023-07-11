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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
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
fun ProductImageCard2(menuItem: Menu, navController: NavHostController,
                      viewModel: MenuViewModel, selectedMenuId: MutableState<List<Int>>,
                      checkedStateList: SnapshotStateList<Boolean>,
                      position: Int,
                      token:String
) {
    val menuViewModel = MenuViewModel()
    val showProductOption = remember { mutableStateOf(false) }
//    var checkedState by remember { mutableStateOf(false) }
////    var showCheckbox by remember { mutableStateOf(false) }
////    val tesIds = arrayListOf(1, 2)
    Card(
        modifier = Modifier
            .height(200.dp)
            .combinedClickable(
                onClick = {
                    viewModel.selectedMenuState.value = menuItem

                    navController.navigate("product_detail_screen/[${menuItem.menu_id}]")
                },
                onLongClick = {
                    viewModel.selectedMenuState.value = menuItem
                    showProductOption.value = true
//                    showCheckbox.value = !showCheckbox.value
//                    checkedStateList[position] = !checkedStateList[position]

//                    navController.navigate("order_detail_screen")
                }
            ),
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
                    if (showProductOption.value) {
                        Row() {
                            IconButton(onClick = {
                                navController.navigate("product_edit_screen/[${menuItem.menu_id}]")
                            }) {

                                Icon(imageVector = Icons.Default.Edit, contentDescription = "")
                            }

                            IconButton(onClick = {
                                menuViewModel.deleteMenuItem(token, menuItem.menu_id)
                                Log.e("delete", menuViewModel.deleteSuccess.value.toString())
                                showProductOption.value = false
                                navController.navigate("product_screen")
                            }) {
                                Icon(imageVector = Icons.Default.Delete, contentDescription = "")
                            }
                        }
                        Button(onClick = {
                            showProductOption.value = false
                        }) {
                            Text(text = "Batal")
                        }
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
fun OrderImageCard3(menuItem: Menu, navController: NavHostController,
                      viewModel: MenuViewModel, selectedMenuId: MutableState<List<Int>>,
//                      showCheckbox:  MutableState<Boolean>,
//                      checkedState: MutableState<Boolean>
                      checkedStateList: SnapshotStateList<Boolean>,
                      position: Int
) {
    var checkedState by remember { mutableStateOf(false) }
    val btnIcon = remember {
        mutableStateOf(Icons.Default.Add)
    }
    Card(
        modifier = Modifier
            .height(200.dp)
            .combinedClickable(
                onClick = {
                    if (menuItem.menu_qty.toInt() != 0) {

                        viewModel.selectedMenuState.value = menuItem
                        Log.e("menuIds", "navController to product detail")

                        checkedState = !checkedState
                        Log.e("ProductItem", "checkedState: $checkedState")
                        btnIcon.value = if (checkedState) {
                            Icons.Default.Check
                        } else {
                            Icons.Default.Add
                        }
                        if (checkedState) {
                            selectedMenuId.value = selectedMenuId.value + menuItem.menu_id
                            Log.e("menuIds", "selectedMenuId: ${selectedMenuId.value}")
                        } else {
                            selectedMenuId.value = selectedMenuId.value - menuItem.menu_id
                            Log.e("menuIds", "selectedMenuId: ${selectedMenuId.value}")
                        }
                    }
                }
            ),
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
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column() {
                        /*Checkbox(
                            checked = checkedState,
                            onCheckedChange = { isChecked ->
                                    checkedState = isChecked
    //                            checkedStateList[position] = isChecked
                            }
                        )*/
                        Text(text = menuItem.menu_name, style = TextStyle(color = Color.White, fontSize = 16.sp))
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Rp${menuItem.menu_price}", style = TextStyle(color = Color.White, fontSize = 16.sp))
                    }
                    if (menuItem.menu_qty.toInt() == 0) {
                        Text(text = "Menu Habis", style = TextStyle(color = Color.Red, fontSize = 16.sp),
                            textAlign = TextAlign.End
                        )
                    } else {
                        IconButton(colors = IconButtonDefaults.iconButtonColors(Primary),modifier = Modifier.clip(CircleShape),onClick = {
                                viewModel.selectedMenuState.value = menuItem
                                Log.e("menuIds", "navController to product detail")

                                checkedState = !checkedState
                                Log.e("ProductItem", "checkedState: $checkedState")
                                btnIcon.value = if (checkedState) {
                                    Icons.Default.Check
                                } else {
                                    Icons.Default.Add
                                }
                                if (checkedState) {
                                    selectedMenuId.value = selectedMenuId.value + menuItem.menu_id
                                    Log.e("menuIds", "selectedMenuId: ${selectedMenuId.value}")
                                } else {
                                    selectedMenuId.value = selectedMenuId.value - menuItem.menu_id
                                    Log.e("menuIds", "selectedMenuId: ${selectedMenuId.value}")
                                }
                        }) {
                            Icon(imageVector = btnIcon.value, contentDescription = "", tint = Color.White, modifier = Modifier.clip(CircleShape))

                        }
//                        Button(
//                            modifier = Modifier.clip(CircleShape),
//                            shape = CircleShape,
//                            onClick = {
//                            viewModel.selectedMenuState.value = menuItem
//                            Log.e("menuIds", "navController to product detail")
//
//                            checkedState = !checkedState
//                            Log.e("ProductItem", "checkedState: $checkedState")
//                            btnIcon.value = if (checkedState) {
//                                Icons.Default.Check
//                            } else {
//                                Icons.Default.Add
//                            }
//                            if (checkedState) {
//                                selectedMenuId.value = selectedMenuId.value + menuItem.menu_id
//                                Log.e("menuIds", "selectedMenuId: ${selectedMenuId.value}")
//                            } else {
//                                selectedMenuId.value = selectedMenuId.value - menuItem.menu_id
//                                Log.e("menuIds", "selectedMenuId: ${selectedMenuId.value}")
//                            }
//                        }) {
//                            Icon(imageVector = btnIcon.value, contentDescription = "")
//                        }
                    }
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
                    navController.navigate("order_detail_screen/[6]")
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
