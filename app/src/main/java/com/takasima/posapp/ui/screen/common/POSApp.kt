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
import androidx.compose.ui.platform.LocalContext
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
import com.takasima.posapp.ui.screen.common.history.InvoiceScreen
import com.takasima.posapp.ui.screen.common.main.ProfileScreen
import com.takasima.posapp.ui.screen.common.order.OrderDetailScreen
import com.takasima.posapp.ui.screen.common.order.OrderScreen
import com.takasima.posapp.ui.screen.common.product.AddMenuScreen
import com.takasima.posapp.ui.screen.common.product.EditProductScreen
import com.takasima.posapp.ui.screen.common.product.ProductDetailScreen
import com.takasima.posapp.ui.screen.common.product.ProductFoodScreen
import com.takasima.posapp.ui.screen.common.product.ProductScreen
import com.takasima.posapp.ui.screen.common.product.ProductOption
import com.takasima.posapp.ui.screen.owner.branch.AddBranchScreen
import com.takasima.posapp.ui.screen.owner.branch.AddStaffScreen
import com.takasima.posapp.ui.screen.owner.branch.ShopBranchScreen
import com.takasima.posapp.ui.screen.owner.branch.StaffScreen
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun POSApp(mainNavController: NavHostController) {
    val ctx = LocalContext.current
    val dataStoreManager = DataStoreManager.getInstance(ctx)
    val storedRole = runBlocking { dataStoreManager.getAuthRole.first() }
    Log.e("storedRole", storedRole.toString())
    val navController = rememberNavController()
//    val snackBarHostState = remember { SnackbarHostState() }
//    val coroutineScope = rememberCoroutineScope()
//    val drawerState  = rememberDrawerState(initialValue = DrawerValue.Closed)
    val openDialog =  remember { mutableStateOf(false) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val altAppBarTitle = when (currentRoute) {
        "profile_screen" -> "Profile"
        "product_add_screen" -> "Add Product"
        "product_detail_screen/{menuId}" -> "Product Detail"
        "order_detail_screen/{menuIds}" -> "Order Detail"
        "branch_add_screen" -> "Add Branch"
        "profile_screen" -> "Profile"

        else -> "Aplikasi"
    }
    val mainAppBarTitle = when (currentRoute) {
        BottomMenu.Orders.route -> BottomMenu.Orders.title
        BottomMenu.Products.route -> BottomMenu.Products.title
        BottomMenu.Histories.route -> BottomMenu.Histories.title
        BottomMenu.Shops.route -> BottomMenu.Shops.title

        else -> "Aplikasi"
    }

    val bottomBarItems = listOf<String>(
        BottomMenu.Orders.route,
        BottomMenu.Products.route,
        BottomMenu.Histories.route,
        BottomMenu.Shops.route
    )
    val bottomBarItem: List<String>
    when (storedRole) {
        "Staff" -> {
            bottomBarItem = bottomBarItems.dropLast(1)
        }
        "Owner" -> {
            bottomBarItem = bottomBarItems.drop(1)
        }
        else -> {
            bottomBarItem = bottomBarItems
        }
    }
    Log.d("BottomBarItem", "bottomBarItem: $bottomBarItem")

    Scaffold(
        topBar = {
                if (currentRoute in bottomBarItem) {
                    CustomTopBar(
                        title = mainAppBarTitle,
                        openDialog = openDialog,
                        navController
                    )
                } else {
                    BackTopBar(title = altAppBarTitle, navController = navController, storedRole!!)
                }
            },
        bottomBar = {
            if (currentRoute in bottomBarItem) {
                    BottomMenuScreen(navController, storedRole)
            }
        }
    ){paddingValues ->

        NavHost(navController,
            startDestination = when (storedRole) {
                "Staff" -> BottomMenu.Orders.route
                "Owner" -> BottomMenu.Products.route
                else -> BottomMenu.Orders.route
                                                 },
            Modifier.padding(paddingValues)) {
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

            composable("product_detail_screen/{menuId}",
                arguments = listOf(navArgument("menuId") { type = NavType.StringType })
            ) {backStackEntry ->
                val menuId = backStackEntry.arguments?.getString("menuId")
//                val menuIdsList = menuIdsArray?.toList()?.map { it.toInt() }?.toCollection(ArrayList())
                Log.e("POSApp product detail", "go to product detail $menuId")
                ProductDetailScreen(navController, menuId!!)
            }

            composable("product_edit_screen/{menuId}",
                arguments = listOf(navArgument("menuId") { type = NavType.StringType })
            ) {backStackEntry ->
                val menuId = backStackEntry.arguments?.getString("menuId")
//                val menuIdsList = menuIdsArray?.toList()?.map { it.toInt() }?.toCollection(ArrayList())
                Log.e("POSApp product detail", "go to product detail $menuId")
                EditProductScreen(navController, menuId!!)
            }

            composable("order_detail_screen/{menuIds}",
                arguments = listOf(navArgument("menuIds") { type = NavType.StringType })
            ) {backStackEntry ->
                val menuIds = backStackEntry.arguments?.getString("menuIds")
                Log.e("POSApp order detail", "go to order detail $menuIds")
                OrderDetailScreen(navController, menuIds!!)
            }

            composable("product_food_screen") {
                ProductFoodScreen(navController)
            }
            composable("product_drink_screen") {
                ProductDrinkScreen(navController)
            }
            composable("branch_add_screen") {
                AddBranchScreen(navController)
            }
            composable("invoice_screen/{orderId}",
                arguments = listOf(navArgument("orderId") { type = NavType.IntType })
            ) {backStackEntry ->
                val orderId = backStackEntry.arguments?.getInt("orderId")
                Log.e("POSApp invoice", "go to invoice $orderId")
                InvoiceScreen(navController, orderId!!)
            }

            composable("branch_staff_screen/{branchId}",
                arguments = listOf(navArgument("branchId") { type = NavType.IntType })
            ) {backStackEntry ->
                val branchId = backStackEntry.arguments?.getInt("branchId")
                Log.e("POSApp order detail", "go to order detail $branchId")
                StaffScreen(navController, branchId!!)
            }
            composable("add_staff_screen/{branchId}",
                arguments = listOf(navArgument("branchId") { type = NavType.IntType })
            ) {backStackEntry ->
                val branchId = backStackEntry.arguments?.getInt("branchId")
                Log.e("POSApp order detail", "go to order detail $branchId")
                AddStaffScreen(navController, branchId!!)
            }
        }
    }

}



@Preview(showBackground = true)
@Composable
fun POSAppPrev() {
}