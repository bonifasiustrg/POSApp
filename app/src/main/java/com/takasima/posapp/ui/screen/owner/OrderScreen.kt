package com.takasima.posapp.ui.screen.owner

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.takasima.posapp.R
import com.takasima.posapp.ui.components.CardInfo
import com.takasima.posapp.ui.components.OrderImageCard
import com.takasima.posapp.ui.components.ProductImageCard

@Composable
fun OrderScreen(navController: NavController) {
////    Text(text = "Order Screen")
//        Column(
//            Modifier
//    //            .scrollable(scrollState, Orientation.Vertical)
//                .verticalScroll(rememberScrollState())
//                .padding(16.dp),
//
//
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            CardInfo("Pemasukan", "Rp. 1.000.000")
//            CardInfo("Pengeluaran", "Rp. 1.000.000")
//            CardInfo("Penjualan", "50%")
//
//            TransactionSection()
//        }
        LazyVerticalGrid(modifier = Modifier.padding(horizontal = 4.dp),
            columns = GridCells.Fixed(2),
            content =  {
                items(25) {
                    Box(modifier = Modifier.padding(8.dp)) {
                        OrderImageCard()
                    }
                }
            }

        )
    }



    @Composable
    fun TransactionSection() {
        Column(Modifier.fillMaxWidth()) {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Transaksi", modifier = Modifier.padding(16.dp))
                Spacer(modifier = Modifier.weight(2f))
                Text(text = "Transaksi", modifier = Modifier.padding(16.dp))
            }
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = { /*TODO*/ },
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 6.dp)) {
                    Text(text = "Hari ini")
                }
                Button(onClick = { /*TODO*/ }, modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 6.dp)) {
                    Text(text = "1 Minggu")
                }
                Button(onClick = { /*TODO*/ }, modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 6.dp)) {
                    Text(text = "1 Bulan")
                }
            }
            Text(text = "Konten 1")



            // Konten lain di dalam Column
            Text(text = "Konten 2")
        }

}
