package com.takasima.posapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.takasima.posapp.R
import com.takasima.posapp.ui.theme.Primary
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun CustomTopBar(
    snackbarHostState: SnackbarHostState, coroutineScope: CoroutineScope,
    drawerState: DrawerState, openDialog: MutableState<Boolean>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(48.dp)
            .background(Color.White)
    ) {
        Card(
            modifier = Modifier.requiredHeight(50.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent,
                contentColor = Primary
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
//                    .padding(8.dp)
            ) {
                IconButton(onClick = {
                    coroutineScope.launch {
//                        snackbarHostState.showSnackbar("Snackbar")
                        drawerState.open()
                    }
                }) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu Draw")
                }
                Text(text = "Beranda", fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(2f)
                )

                IconButton(onClick = { /*TODO*/ }) {

                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = "Message icon",
//                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {

                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "Notifications icon",
//                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
                Image(painter = painterResource(id = R.drawable.profile_photo), contentDescription = "",
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .clickable {
                            openDialog.value = true
                        }

                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CustomTopBarPrev() {
    CustomTopBar(snackbarHostState =remember { SnackbarHostState() }, coroutineScope = rememberCoroutineScope(),
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed), openDialog = remember { mutableStateOf(false) })
}