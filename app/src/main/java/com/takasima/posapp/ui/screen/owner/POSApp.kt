package com.takasima.posapp.ui.screen.owner

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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.takasima.posapp.BottomMenu
import com.takasima.posapp.ui.components.BottomMenuScreen
import com.takasima.posapp.ui.components.CustomTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun POSApp() {
    val navController = rememberNavController()
//    Text(text = "Dashboard Owner Screen")
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val drawerState  = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scrollState = rememberScrollState()
    val openDialog =  remember { mutableStateOf(false) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val appBarTitle = when (currentRoute) {
        BottomMenu.Orders.route -> BottomMenu.Orders.title
        BottomMenu.Products.route -> BottomMenu.Products.title
        BottomMenu.Histories.route -> BottomMenu.Histories.title
        BottomMenu.Shops.route -> BottomMenu.Shops.title
        else -> "Aplikasi"
    }

    Scaffold(
        topBar = { CustomTopBar(
            title = appBarTitle,
            snackbarHostState = snackBarHostState,
            coroutineScope = coroutineScope,
            drawerState = drawerState,
            openDialog = openDialog
        )},
        bottomBar = {
//            CustomBottomNavigation(menuItems, navController)
            BottomMenuScreen(navController)
        }
    ){paddingValues ->
        NavHost(navController, startDestination = BottomMenu.Orders.route, Modifier.padding(paddingValues)) {
            /*composable("welcome_screen"){
                WelcomeScreen(navController = navController)
            }
            composable("login_screen"){
                LoginScreen(navController = navController)
            }
            composable("posapp"){
                POSApp(navController = navController)
            }*/
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
        }
    }

}



@Preview(showBackground = true)
@Composable
fun POSAppPrev() {
}