package com.takasima.posapp.ui.screen.common.history

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.takasima.posapp.models.HistoryViewModel
import com.takasima.posapp.models.MenuViewModel
import com.takasima.posapp.ui.theme.Neutral
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@Composable
fun InvoiceScreen(navController: NavHostController, orderId: Int) {
    val context = LocalContext.current
    val dataStoreManager = remember { DataStoreManager.getInstance(context) }
    val storedToken = runBlocking { dataStoreManager.getAuthToken.first() }

    val historyViewModel: HistoryViewModel = viewModel()

    Log.e("tes", "$orderId")
    val orderInvoice by historyViewModel.orderDataState

    // Fetch menu data when the screen is displayed
    LaunchedEffect(Unit) {
        storedToken?.let { historyViewModel.getOrderById(storedToken, orderId) }
    }



    Surface(modifier = Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {

            when (orderInvoice) {
                null -> {
                    // Menampilkan indikator loading atau pesan lainnya saat data sedang diambil
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                    Log.e("tes", "menuListState $orderInvoice")
                }
                else -> {

                    Column(
                        Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.9f)
                            .background(Neutral)
                            .clip(RoundedCornerShape(16.dp))
                            .verticalScroll(
                                state = rememberScrollState(),
                                enabled = true,
                                reverseScrolling = true
                            ).padding(16.dp)
                        , verticalArrangement = Arrangement.Center,

                        ) {
//                        Text(text = "Invoice Screen id=$orderId")
                        Text(text = "Oleh:", fontWeight = FontWeight.Bold)
                        Text(text = orderInvoice?.data?.cashier ?: "Anonym Cashier", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(24.dp))

                        Text(text = "Informasi",fontWeight = FontWeight.Bold)
                        Text(text = "Jumlah: Rp${orderInvoice?.data?.total_price}", fontWeight = FontWeight.Bold)
                        Text(text = "Date: ${orderInvoice?.data?.date}")
                        Text(text = "No: ${orderInvoice?.data?.no_order}")
        //            Text(text = "Dues: ")
                        Divider(
                            Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp), thickness = 2.dp, color = Color.Black)
                        Row(
                            Modifier
                                .fillMaxWidth()) {
                            Text(text = "PRODUK", modifier = Modifier.weight(1f))
                            Text(text = "KUANTITAS", modifier = Modifier.weight(1f))
                            Text(text = "HARGA", modifier = Modifier.weight(1f))
                            Text(text = "JUMLAH", modifier = Modifier.weight(1f))
                        }

                        LazyColumn(
                            Modifier
                                .fillMaxWidth()
                                .height(200.dp)) {
                            items(orderInvoice!!.data!!.orderList.size) { index ->
//                                Log.e("invoice screen", "${orderInvoice!!.data!!.orderList[index]}")
                                val jlhHarga = orderInvoice!!.data!!.orderList[index].qty * orderInvoice!!.data!!.orderList[index].price
                                Row(
                                    Modifier
                                        .fillMaxWidth()
                                        .weight(1f)) {
                                    Text(text = orderInvoice!!.data!!.orderList[index].nama_menu, modifier = Modifier.weight(1f), fontSize = 14.sp)
                                    Text(text = orderInvoice!!.data!!.orderList[index].qty.toString(), modifier = Modifier.weight(1f), fontSize = 14.sp)
                                    Text(text = "Rp${orderInvoice!!.data!!.orderList[index].price.toString()}", modifier = Modifier.weight(1f), fontSize = 14.sp)
                                    Text(text = jlhHarga.toString(), modifier = Modifier.weight(1f), fontSize = 14.sp)
                                }
                            }
                        }


                        Divider(
                            Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp), thickness = 2.dp, color = Color.Black)

                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = "Total Harga")
                            Text(text = "Rp${orderInvoice?.data?.total_price}", fontWeight = FontWeight.Bold)
                        }
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = "GST(0%)")
                            Text(text = "Rp0", fontWeight = FontWeight.Bold)
                        }
                        Divider(
                            Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp), thickness = 2.dp, color = Color.Black)

                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = "Total Pesanan", fontWeight = FontWeight.Bold)
                            Text(text = "Rp${orderInvoice?.data?.total_price}", fontWeight = FontWeight.Bold, fontSize = 32.sp)
                        }
                        Spacer(modifier = Modifier.height(24.dp))


                    }
                }
            }
            OutlinedButton(onClick = {
                navController.navigate("history_screen")
            },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Back", fontWeight = FontWeight.Bold)
            }
        }

    }
}