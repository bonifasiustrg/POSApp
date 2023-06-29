package com.takasima.posapp.ui.screen.owner

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.takasima.posapp.R
import com.takasima.posapp.ui.components.CardInfo
import com.takasima.posapp.ui.components.CustomTopBar
import com.takasima.posapp.ui.components.SideBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController) {
    Text(text = "Dashboard Owner Screen")
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val drawerState  = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scrollState = rememberScrollState()
    val openDialog =  remember { mutableStateOf(false) }


    ModalNavigationDrawer(drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth(0.7f)
            ) {
                SideBar(coroutineScope, drawerState, openDialog, navController)

            }
        }) {
        Scaffold(
            topBar = { CustomTopBar(
                snackbarHostState = snackBarHostState,
                coroutineScope = coroutineScope,
                drawerState = drawerState,
                openDialog = openDialog
            )},
            /*bottomBar = { HomeBottomMenu() },
            floatingActionButton = { GmailFab(scrollState = scrollState)}*/
        ){paddingValues ->
//            Box(modifier = Modifier.fillMaxWidth().padding(it))
            // padding of the scaffold is enforced to be used

//            MailList(paddingValues = paddingValues, scrollState)

            Column(
                Modifier
                    .scrollable(scrollState, Orientation.Vertical)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),


                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(text = "Dashboard Owner Screen", modifier = Modifier.padding(paddingValues))
                CardInfo("Pemasukan", "Rp. 1.000.000")
                CardInfo("Pengeluaran", "Rp. 1.000.000")
                CardInfo("Penjualan", "50%")

                TransactionSection()
            }
        }
    }
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

        LazyColumn(Modifier.height(200.dp)) {

            items(100) { index ->
                TransactionItem()
            }
        }

        // Konten lain di dalam Column
        Text(text = "Konten 2")
    }

}

@Composable
fun TransactionItem() {
    Row(Modifier.fillMaxWidth().padding(16.dp)) {
        Icon(painter = painterResource(id = R.drawable.transaction_item), contentDescription = "transaction_item",
            modifier = Modifier.padding(end = 16.dp).size(24.dp)
        )
        Column {
            Text(text = "BDI Restaurant", fontWeight = FontWeight.Bold)
            Text(text = "Rp. 1.000.000")
        }
        Text(text = "13.46", textAlign = TextAlign.End, modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardPrev() {
    DashboardScreen(navController = rememberNavController())
}
@Preview(showBackground = true)
@Composable
fun DashboardElmPrev() {
//    TransactionSection()
    TransactionItem()
}