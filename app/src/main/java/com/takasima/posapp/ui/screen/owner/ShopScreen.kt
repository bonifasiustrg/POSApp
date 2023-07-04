package com.takasima.posapp.ui.screen.owner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.takasima.posapp.R
import com.takasima.posapp.ui.theme.Neutral

@Composable
fun ShopBranchScreen(navController: NavController) {
//    Text(text = "Shop Branch Screen")

    Column(Modifier.fillMaxSize()) {
        LazyColumn() {

            items(4) { index ->
                BranchItem()
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}


@Composable
fun BranchItem() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Neutral)

    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "BDI Coffee", fontWeight = FontWeight.Bold)
            Text(text = "Jl. Soekarno Hatta BDG")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ShopPrev() {
    ShopBranchScreen(rememberNavController())
}