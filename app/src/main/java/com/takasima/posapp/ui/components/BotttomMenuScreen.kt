package com.takasima.posapp.ui.components

import android.util.Log
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.takasima.posapp.ui.theme.Primary

@Composable
fun BottomMenuScreen(navController: NavController, storedRole: String?) {
    val menuItemss = listOf(
        BottomMenu.Orders,
        BottomMenu.Products,
        BottomMenu.Histories,
        BottomMenu.Shops
    )
    val menuItems: List<BottomMenu>
    when (storedRole) {
        "Staff" -> {
            menuItems = menuItemss.dropLast(1)
        }
        "Owner" -> {
            menuItems = menuItemss.drop(1)
        }
        else -> {
            menuItems = menuItemss
        }
    }

    Log.d("BottomMenuScreen", "menuItems: $menuItems")
    NavigationBar(contentColor = Color.Black /*item color*/,
        containerColor = Color.White /*Bot nav container color*/,
        modifier = Modifier.height(78.dp).clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
    ) {
        val navBackStateException by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStateException?.destination?.route //check current route

        menuItems.forEach {
            val selected = currentRoute == it.route
            NavigationBarItem(selected = selected,
                label = { Text(text = it.title)},
//                label = { Text(text = it.title, color = if (selected) Color.Black else Color.Gray)},
                alwaysShowLabel = selected,
//                icon = { Icon(imageVector = it.icon, contentDescription = "",modifier = Modifier.size(30.dp)
////                    if (selected) 40.dp else 20.dp)
////                , tint = Color.Black
//                )
                icon = { Icon(painter = painterResource(id = it.icon), contentDescription = "",modifier = Modifier.size(30.dp)
//                    if (selected) 40.dp else 20.dp)
//                , tint = Color.Black
                )
                    
                },/*
                icon = { Icon(imageVector = it.icon, contentDescription = "",
                    tint = if (selected) Color.Black else Color.Gray
                )},*/
                onClick = {
                    navController.navigate(it.route) {
                        navController.graph.startDestinationRoute?.let {route ->
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
//                    selectedIconColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),
                    selectedIconColor = Primary,
                    selectedTextColor = Primary,
                    unselectedTextColor = Color.Gray,
                    unselectedIconColor = Color.Gray
                )
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BottomMenuPrev() {
    BottomMenuScreen(navController = rememberNavController(), storedRole = "Staff")
}