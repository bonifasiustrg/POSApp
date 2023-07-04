package com.takasima.posapp.ui.components


import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.takasima.posapp.R
import com.takasima.posapp.ui.theme.Neutral
import com.takasima.posapp.ui.theme.PrimarySecond

sealed class BottomMenuData(
    val icon: ImageVector,
    val title: String
){
    object Mail: BottomMenuData(
        icon = Icons.Filled.Email,
        title = "Mail"
    )
    object Meet: BottomMenuData(
        icon = Icons.Filled.Phone,
        title = "Meet"
    )
}

@Composable
fun CustomBottomNavigation(navController: NavController) {
    NavigationBar() {

    }
}

@Composable
fun HomeBottomMenu(navController: NavController) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf(
        BottomMenuData.Mail,
        BottomMenuData.Mail,
        BottomMenuData.Mail,
        BottomMenuData.Meet
    )

    NavigationBar() {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                label = { Text(text = item.title)},
//                selected = selectedItem == index,
                selected = false,
                alwaysShowLabel = true,
                onClick = {
                    selectedItem = index
                    when (index) {
                        0 -> navController.navigate("dashboard_screen")
                        1 -> navController.navigate("product_screen")
                        2 -> navController.navigate("history_screen")
                        3 -> navController.navigate("shop_screen")
                    }
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) }
            )
        }
    }
}




data class BottomMenuContent(
    val title:String,
    @DrawableRes val iconId: Int
)

@Composable
fun BottomNav(navController: NavController) {
    Box(modifier = Modifier
        .fillMaxWidth()
    ) {
        val items = listOf<BottomMenuContent>(
            BottomMenuContent("Order", R.drawable.order_off),
            BottomMenuContent("Product  ", R.drawable.cart_off),
            BottomMenuContent("History", R.drawable.history_off),
            BottomMenuContent("Shop", R.drawable.shop_branch_off)
        )

        BottomNavMenu(
            items = items,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            navController = navController
        )
    }
}


@Composable
fun BottomNavMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = Neutral,
    activeTextColor: Color = PrimarySecond,
    inactiveTextColor: Color = Color.Black,
    initialSelectedItemIndex: Int = 0,
    navController: NavController
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }

    Row(modifier = modifier
        .fillMaxWidth()
        .background(Color.White)
        .padding(horizontal = 15.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,

        ) {
        items.forEachIndexed { index, item ->
            BottomNavMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex = index

                when(index){
                    0 -> navController.navigate("dashboard_screen")
                    1 -> navController.navigate("product_screen")
                    2 -> navController.navigate("history_screen")
                    3 -> navController.navigate("shop_screen")
                }
            }
        }
    }
}

@Composable
fun BottomNavMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = Color.Transparent,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = Color.Black,
    onItemClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ) {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if (isSelected) activeHighlightColor else Color.Transparent
                )
                .padding(10.dp)
        ){
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)

            )
        }

        Text(text = item.title,
            color = if (isSelected) activeTextColor else inactiveTextColor
        )

    }
}

@Preview
@Composable
fun BottomNavPrev() {
}
