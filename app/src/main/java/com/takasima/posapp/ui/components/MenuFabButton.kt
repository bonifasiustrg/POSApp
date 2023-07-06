package com.takasima.posapp.ui.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.takasima.posapp.ui.theme.Primary

@Composable
fun MenuFabButton(scrollState: ScrollState, navController: NavHostController) {
    FloatingActionButton(
        onClick = {
            navController.navigate("product_setting_screen")
        },
        modifier = Modifier.background(Color.Transparent),
        shape = CircleShape, containerColor = Primary
    ) {
        Icon(imageVector = Icons.Default.Settings, contentDescription = null)

    }
    // fab will shange if pass specifuc scrollstate value
    /*if (scrollState.value==0) {


    } else   {

        ExtendedFloatingActionButton(
            text = { Text(text = "Tambah Menu", fontSize = 16.sp)  },
            onClick = { *//*TODO*//* },
            icon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
            modifier = Modifier.background(Primary)
        )
    }*/

}