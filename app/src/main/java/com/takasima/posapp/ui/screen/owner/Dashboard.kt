package com.takasima.posapp.ui.screen.owner

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
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.takasima.posapp.R
import com.takasima.posapp.ui.components.CustomTopBar
import com.takasima.posapp.ui.components.SideBar
import com.takasima.posapp.ui.components.WidthButton
import kotlinx.coroutines.launch

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

            Text(text = "Dashboard Owner Screen", modifier = Modifier.padding(paddingValues))
//            MailList(paddingValues = paddingValues, scrollState)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardPrev() {
    DashboardScreen(navController = rememberNavController())
}