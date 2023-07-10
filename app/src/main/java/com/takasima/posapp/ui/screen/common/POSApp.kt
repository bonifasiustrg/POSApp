package com.takasima.posapp.ui.screen.common

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.takasima.posapp.ui.components.BottomMenu
import com.takasima.posapp.ui.components.BackTopBar
import com.takasima.posapp.ui.components.BottomMenuScreen
import com.takasima.posapp.ui.components.CustomProductTopBar
import com.takasima.posapp.ui.components.CustomTopBar
import com.takasima.posapp.ui.screen.common.history.HistoryScreen
import com.takasima.posapp.ui.screen.common.main.ProfileScreen
import com.takasima.posapp.ui.screen.common.order.OrderDetailScreen
import com.takasima.posapp.ui.screen.common.order.OrderScreen
import com.takasima.posapp.ui.screen.common.product.AddMenuScreen
import com.takasima.posapp.ui.screen.common.product.ProductDetailScreen
import com.takasima.posapp.ui.screen.common.product.ProductFoodScreen
import com.takasima.posapp.ui.screen.common.product.ProductScreen
import com.takasima.posapp.ui.screen.common.product.ProductOption
import com.takasima.posapp.ui.screen.owner.branch.AddBranchScreen
import com.takasima.posapp.ui.screen.owner.branch.ShopBranchScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun POSApp(mainNavController: NavHostController) {

    val navController = rememberNavController()
//    Text(text = "Dashboard Owner Screen")
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val drawerState  = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scrollState = rememberScrollState()
    val openDialog =  remember { mutableStateOf(false) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val mainAppBarTitle = when (currentRoute) {
        BottomMenu.Orders.route -> BottomMenu.Orders.title
        BottomMenu.Products.route -> BottomMenu.Products.title
        BottomMenu.Histories.route -> BottomMenu.Histories.title
        BottomMenu.Shops.route -> BottomMenu.Shops.title

        else -> "Aplikasi"
    }
    val altAppBarTitle = when (currentRoute) {
        "profile_screen" -> "Profile"
        else -> "Aplikasi"
    }

    Scaffold(
        topBar = {
            if (currentRoute == BottomMenu.Products.route) {
                CustomProductTopBar(
                    openDialog = openDialog,
                    navController = navController
                )
            } else if (currentRoute != "profile_screen") {
//            CustomBottomNavigation(menuItems, navController)
                CustomTopBar(
                    title = mainAppBarTitle,
                    snackbarHostState = snackBarHostState,
                    coroutineScope = coroutineScope,
                    drawerState = drawerState,
                    openDialog = openDialog,
                    navController
                )
            } else {
                BackTopBar(title = altAppBarTitle, navController = navController)
            }
            },
        bottomBar = {
            if (currentRoute != "profile_screen") {
//            CustomBottomNavigation(menuItems, navController)
                    BottomMenuScreen(navController)
            }
        }
    ){paddingValues ->
        NavHost(navController, startDestination = BottomMenu.Orders.route, Modifier.padding(paddingValues)) {
            composable(BottomMenu.Orders.route){
                OrderScreen(navController = navController)
            }
            composable(BottomMenu.Products.route){
                ProductScreen(navController = navController)
            }

            composable(BottomMenu.Histories.route){
                HistoryScreen(navController = navController)
            }

            composable(BottomMenu.Shops.route){
                ShopBranchScreen(navController = navController)
            }
            composable("profile_screen"){
                ProfileScreen(mainNavController)
            }
            composable("product_setting_screen") {
                ProductOption(navController)
            }
            composable("product_add_screen") {
                AddMenuScreen(navController)
            }

            composable("product_detail_screen/{menuIds}",
                arguments = listOf(navArgument("menuIds") { type = NavType.IntType})
            ) {backStackEntry ->
                val menuIds = backStackEntry.arguments?.getInt("menuIds")
//                val menuIdsList = menuIdsArray?.toList()?.map { it.toInt() }?.toCollection(ArrayList())
                Log.e("menuIds", "go to product detail")
                ProductDetailScreen(navController, menuIds!!)
            }

            composable("order_detail_screen/{menuIds}",
                arguments = listOf(navArgument("menuIds") { type = NavType.StringType })
            ) {backStackEntry ->
                val menuIds = backStackEntry.arguments?.getString("menuIds")
//                val menuIdsList = menuIdsArray?.toList()?.map { it.toInt() }?.toCollection(ArrayList())
                Log.e("POSApp order detail", "go to order detail")
                OrderDetailScreen(navController, menuIds!!)
            }
            /*composable("order_detail_screen") {
                OrderDetailScreen(navController)
            }*/
            composable("product_food_screen") {
                ProductFoodScreen(navController)
            }
            composable("product_drink_screen") {
                ProductDrinkScreen(navController)
            }
            composable("branch_add_screen") {
                AddBranchScreen(navController)
            }

        }
    }

}



@Preview(showBackground = true)
@Composable
fun POSAppPrev() {
}