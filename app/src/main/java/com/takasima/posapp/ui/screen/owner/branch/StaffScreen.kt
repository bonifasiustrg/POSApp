package com.takasima.posapp.ui.screen.owner.branch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.takasima.posapp.data.branch.Staff
import com.takasima.posapp.models.BranchViewModel
import com.takasima.posapp.ui.components.HeadingTextComponent3
import com.takasima.posapp.ui.theme.PrimarySecond
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StaffScreen(navController: NavHostController, branchId:Int) {
    val ctx = LocalContext.current
    val dataStoreManager = DataStoreManager.getInstance(ctx)
    val storedToken = runBlocking { dataStoreManager.getAuthToken.first() }
    val viewModel: BranchViewModel = viewModel()
    val staffList by viewModel.staffList


    LaunchedEffect(Unit) {
        storedToken?.let { viewModel.getStaffList(storedToken, branchId) }
    }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                containerColor = PrimarySecond,
                contentColor = Color.White,
                onClick = {
                navController.navigate("add_staff_screen/$branchId")
            },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Default.Person, contentDescription ="" )
                Text(text = "Tambahkan Staff")
            }
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = "Daftar Staff Branch $branchId", modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 24.sp, fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(24.dp))
            LazyColumn {
                staffList?.data?.let {
                    items(it.size) { staff ->
                        StaffCard(staffList!!.data[staff])
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }

            Button(
                colors = ButtonDefaults.buttonColors(PrimarySecond),
                onClick = {
                navController.navigate("shop_screen") {
                    navController.popBackStack()
                }
            }) {
                Text(text = "Kembali")
            }
        }
    }
}

@Composable
fun StaffCard(staffItem: Staff) {
    Column(Modifier.fillMaxWidth()) {
        HeadingTextComponent3(value = "Staff Id")
        Text(text = staffItem.staff_id.toString())
        HeadingTextComponent3(value = "Nama Lengkap")
        Text(text = staffItem.user.name)
        HeadingTextComponent3(value = "No HP")
        Text(text = staffItem.no_hp)
        HeadingTextComponent3(value = "Alamat")
        Text(text = staffItem.address)
        HeadingTextComponent3(value = "Email")
        Text(text = staffItem.user.email)
        HeadingTextComponent3(value = "Tanggal akun dibuat")
        Text(text = staffItem.user.created_at)
    }
}