package com.takasima.posapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.takasima.posapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SideBar(coroutineScope:CoroutineScope, drawerState: DrawerState, openDialog: MutableState<Boolean>, navController: NavController){
    val menuList = listOf(
        Icons.Filled.MailOutline to "Beranda",
        Icons.Filled.Email to "Transaksi",
        Icons.Filled.Email to "Produk",
        Icons.Filled.Star to "Cabang"
    )
    var selectedItem by remember { mutableStateOf(-1) }


    Column(Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)) {
        IconButton(
            modifier = Modifier
                .align(alignment = Alignment.End),
            onClick = {
                coroutineScope.launch {
//                        snackbarHostState.showSnackbar("Snackbar")
                    drawerState.close()
                }
            }) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu Draw",
            )
        }
        Image(painter = painterResource(id = R.drawable.profile_photo), contentDescription = "",
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .background(Color.White)
                .clickable {
                    openDialog.value = true
                },
            Alignment.Center
        )
        menuList.forEachIndexed(){idx, data->
            NavigationDrawerItem(
                label = {
                    Row(modifier = Modifier
                        .fillMaxWidth()){
                        Icon(
                            imageVector = data.first,
                            contentDescription = data.second,
                        )
                        Text(text = data.second, modifier = Modifier.padding(start = 8.dp))

                    }
                },
                selected = selectedItem == idx,
                onClick = {
                    selectedItem = idx
                    // NAV CONTROLLER EHRE
                }
            )
        }
        Spacer(modifier = Modifier.height(150.dp))
        WidthButton(value = "LOGOUT",
            onClick = {
                navController.navigate("welcome_screen")
            })
    }
}