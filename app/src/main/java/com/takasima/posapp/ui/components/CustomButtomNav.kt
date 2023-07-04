package com.takasima.posapp.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.takasima.posapp.R

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Order : BottomNavItem("Home", R.drawable.order_off,"order")
    object Product: BottomNavItem("Product",R.drawable.cart_off,"my_network")
    object History: BottomNavItem("History",R.drawable.history_off,"add_post")
    object Shop: BottomNavItem("Shop",R.drawable.shop_branch_off,"Shop")
}

@Composable
fun CustomBottomNavigation(menuItems:  List<Pair<Int, String>>, /*callback: (String) -> Unit,*/ navController: NavController) {
    var selectedIndex by remember { mutableStateOf(0) }


    /*NavigationBar() {
        menuItems.forEachIndexed { index, data ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = {
                    selectedIndex = index
                    callback(data.second)

                    navController.navigate(data)
                },
                label = { Text(text = data.second)},
                icon = { Icon(painter = painterResource(id = data.first), contentDescription = "Home", modifier = Modifier.size(30.dp)) }
            )
        }
    }*/

    val items = listOf(
        BottomNavItem.Order,
        BottomNavItem.Product,
        BottomNavItem.History,
        BottomNavItem.Shop

    )
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {
                        navController.graph.startDestinationRoute?.let {screen_route->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = { Text(text = item.title)},
                icon = { Icon(painter = painterResource(id = item.icon), contentDescription = "Home", modifier = Modifier.size(30.dp)) }
            )
        }
    }
}