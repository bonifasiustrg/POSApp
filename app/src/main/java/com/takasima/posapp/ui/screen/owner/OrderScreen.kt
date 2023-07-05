package com.takasima.posapp.ui.screen.owner

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.takasima.posapp.BottomMenu
import com.takasima.posapp.R
import com.takasima.posapp.ui.components.CardInfo
import com.takasima.posapp.ui.components.OrderImageCard
import com.takasima.posapp.ui.components.ProductImageCard
import com.takasima.posapp.ui.components.TabLayoutScreen
import com.takasima.posapp.ui.components.tabs
import com.takasima.posapp.ui.screen.ProfileScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(navController: NavController) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val orderNavController = rememberNavController()

    Scaffold(
        /*topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Jetpack Compose TabLayout")},
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors (
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }*/
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            TabRow(
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
            }
        }
    }
}
        /*NavHost(orderNavController, startDestination = "food_screen"){
            composable("food_screen"){
                FoodScreen(navController = orderNavController)
            }
            composable("drink_screen"){
                DrinkScreen(navController = orderNavController)
            }
        }*/


@Preview(showBackground = true)
@Composable
fun prev3() {
    OrderScreen(navController = rememberNavController())
}