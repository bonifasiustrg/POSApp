package com.takasima.posapp.ui.screen.common.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.takasima.posapp.R
import com.takasima.posapp.data.history.HistoryData
import com.takasima.posapp.models.BranchViewModel
import com.takasima.posapp.models.HistoryViewModel
import com.takasima.posapp.ui.theme.Neutral
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@Composable
fun HistoryScreen(navController: NavController) {
//    Text(text = "History Screen")
    val ctx = LocalContext.current
    val dataStoreManager = DataStoreManager.getInstance(ctx)
    val storedToken = runBlocking { dataStoreManager.getAuthToken.first() }
    val viewModel: HistoryViewModel = viewModel()
    val listHistory by viewModel.historyListState


    // Fetch menu data when the screen is displayed
    LaunchedEffect(Unit) {
        storedToken?.let { viewModel.fetchHistoryData(it) }
    }

    Column(Modifier.fillMaxSize()) {
        LazyColumn() {

            items(listHistory.size) { index ->
                TransactionItem(historyData = listHistory[index])

            }
        }
    }
}
@Composable
fun TransactionItem(historyData: HistoryData) {
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
            Text(text = historyData.order_id.toString(), fontWeight = FontWeight.Bold)
            Text(text = historyData.user_id)
            Text(text = historyData.order_note)
            Text(text = historyData.order_status)
            Text(text = historyData.created_at)
        }
//        Text(text = "13.46", textAlign = TextAlign.End, modifier = Modifier
//            .weight(1f)
//            .padding(8.dp))
    }
}

@Preview
@Composable
fun HistoryScreenPrev() {
    HistoryScreen(navController = rememberNavController())
}