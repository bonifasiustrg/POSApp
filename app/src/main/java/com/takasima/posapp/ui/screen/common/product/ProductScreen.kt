package com.takasima.posapp.ui.screen.common.product

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.takasima.posapp.ui.components.BottomMenu
import com.takasima.posapp.ui.components.TabItem
import com.takasima.posapp.ui.components.tabs
import com.takasima.posapp.ui.screen.common.ProductDrinkScreen
import com.takasima.posapp.ui.screen.common.ProductFoodScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(navController: NavHostController) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val productNavController = rememberNavController()
    val scrollState = rememberScrollState()

    val tabsProduct = listOf(
        TabItem(
            title = "Makanan",
            screen = { ProductFoodScreen(productNavController) }
        ),
        TabItem(
            title = "Minuman",
            screen = { ProductDrinkScreen(productNavController) }
        )
    )

    Scaffold() { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
            ) {
                tabsProduct.forEachIndexed { index, item ->
                    Tab(
                        selected = index == pagerState.currentPage,
                        text = { Text(text = item.title) },
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                    )
                }
            }
            HorizontalPager(
                pageCount = tabsProduct.size,
                state = pagerState
            ) {
                tabsProduct[pagerState.currentPage].screen()
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun ProductScreenPrev() {
    ProductScreen(rememberNavController())
}