package com.takasima.posapp.ui.components

import com.takasima.posapp.R

sealed class BottomMenu(
    val route: String,
//    val icon: ImageVector,
    val icon: Int,
    val title: String
) {
//    object Orders: BottomMenu("order_screen", Icons.Outlined.Home, "Order")
//    object Products: BottomMenu("product_screen", Icons.Outlined.Home, "Product")
//    object Histories: BottomMenu("history_screen", Icons.Outlined.Home, "History")
//    object Shops: BottomMenu("shop_screen", Icons.Outlined.Home, "Shop")
    object Orders: BottomMenu("order_screen", R.drawable.order_icon, "Pesanan")
    object Products: BottomMenu("product_screen", R.drawable.product_icon, "Produk")
    object Histories: BottomMenu("history_screen", R.drawable.history_icon, "Riwayat")
    object Shops: BottomMenu("shop_screen", R.drawable.branch_icon, "Cabang")
}
