package com.takasima.posapp.ui.screen.owner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun HistoryScreen(navController: NavController) {
//    Text(text = "History Screen")

    Column(Modifier.fillMaxSize()) {
        LazyColumn() {

            items(100) { index ->
                TransactionItem()
            }
        }
    }
}


@Composable
fun TransactionItem() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Neutral)

    ) {

        Icon(painter = painterResource(id = R.drawable.transaction_item), contentDescription = "transaction_item",
            modifier = Modifier
                .padding(8.dp)
                .padding(end = 8.dp)
                .size(24.dp)
        )
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "BDI Restaurant", fontWeight = FontWeight.Bold)
            Text(text = "Rp. 1.000.000")
        }
        Text(text = "13.46", textAlign = TextAlign.End, modifier = Modifier.weight(1f).padding(8.dp))
    }
}

@Preview
@Composable
fun HistoryScreenPrev() {
    HistoryScreen(navController = rememberNavController())
}