package com.takasima.posapp.ui.screen.common.order

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.takasima.posapp.ui.components.TabItem
import com.takasima.posapp.ui.screen.common.DrinkScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(navController: NavHostController) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val orderNavController = rememberNavController()

    val tabs = listOf(
        TabItem(
            title = "Makanan",
            screen = { FoodScreen(navController = navController) }
        ),
        TabItem(
            title = "Minuman",
            screen = { DrinkScreen(navController = navController) }
        )
    )

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = {
                navController.navigate("order_detail_screen/[5,6]")

            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription ="" )
                Text(text = "Add Order")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            /*TabRow(
                selectedTabIndex = pagerState.currentPage,
            ) {
                tabs.forEachIndexed { index, item ->
                    Tab(
                        selected = index == pagerState.currentPage,
                        text = { Text(text = item.title) },
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                    )
                }
            }
            HorizontalPager(
                pageCount = tabs.size,
                state = pagerState
            ) {
                tabs[pagerState.currentPage].screen()
            }*/
            FoodScreen(navController = navController)

        }
    }
}

@Preview(showBackground = true)
@Composable
fun prev3() {
    OrderScreen(navController = rememberNavController())
}