package com.takasima.posapp.ui.screen.common.product

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.takasima.posapp.ui.components.TabItem
import com.takasima.posapp.ui.components.WidthButton
import com.takasima.posapp.ui.screen.common.ProductDrinkScreen
import com.takasima.posapp.ui.screen.owner.branch.AddBranchScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(navController: NavHostController) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val productNavController = rememberNavController()
    val scrollState = rememberScrollState()
    var branchId by remember { mutableStateOf(0) }
    val productByBranchButtonClicked = remember { mutableStateOf(false) }

    val tabsProduct = listOf(
        TabItem(
            title = "Makanan",
            screen = { ProductFoodScreen(navController) }
        ),
        TabItem(
            title = "Minuman",
            screen = { ProductDrinkScreen(navController) }
        )
    )

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = {
                navController.navigate("product_add_screen")
            }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add",
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(text = "Add Menu")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            /*TabRow(
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
            }*/

            ProductFoodScreen(navController)
        }
    }
}




@Preview(showBackground = true)
@Composable
fun ProductScreenPrev() {
    ProductScreen(rememberNavController())
}